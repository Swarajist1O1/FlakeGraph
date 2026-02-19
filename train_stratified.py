import os
import glob
import random
import numpy as np
import torch
import torch.nn.functional as F
from torch_geometric.loader import DataLoader
from torch_geometric.nn import GINConv, global_add_pool
from torch_geometric.utils import dropout_edge
from collections import Counter

# --- 1. REPRODUCIBILITY ---
SEED = 42
random.seed(SEED)
np.random.seed(SEED)
torch.manual_seed(SEED)

# --- CONFIGURATION ---
DATA_DIR = "processed_tensors/train"
BATCH_SIZE = 64
HIDDEN_CHANNELS = 128
EPOCHS = 30
LEARNING_RATE = 0.001

print(f"Loading tensors from: {os.path.abspath(DATA_DIR)}")
files = glob.glob(os.path.join(DATA_DIR, "*.pt"))
dataset = []
labels = []

if not files:
    print("No files found!")
    exit()

for file in files:
    try:
        data = torch.load(file, weights_only=False)
        dataset.append(data)
        labels.append(int(data.y.item()))
    except:
        pass

# --- 2. CALCULATE CLASS WEIGHTS ---
# Instead of a Sampler, we calculate a penalty multiplier for the Loss Function
counts = Counter(labels)
# Weight for class i = total_samples / (num_classes * count_i)
# This usually results in a high weight for Flaky (1) and low for Safe (0)
weight_safe = len(labels) / (2 * counts[0])
weight_flaky = len(labels) / (2 * counts[1])
class_weights = torch.tensor([weight_safe, weight_flaky], dtype=torch.float)

print(f"   Class Counts: Safe={counts[0]}, Flaky={counts[1]}")
print(f"   Loss Weights Applied: Safe={weight_safe:.2f}, Flaky={weight_flaky:.2f}")

# --- 3. DATALOADER (Standard Shuffle, no Sampler) ---
train_loader = DataLoader(dataset, batch_size=BATCH_SIZE, shuffle=True)

# --- 4. MODEL (GreedyGIN - 128 Channels) ---
class FlakyGreedyGIN(torch.nn.Module):
    def __init__(self, hidden_channels):
        super(FlakyGreedyGIN, self).__init__()
        self.mlp1 = torch.nn.Sequential(
            torch.nn.Linear(768, hidden_channels),
            torch.nn.BatchNorm1d(hidden_channels),
            torch.nn.ReLU(),
            torch.nn.Linear(hidden_channels, hidden_channels)
        )
        self.conv1 = GINConv(self.mlp1)
        self.mlp2 = torch.nn.Sequential(
            torch.nn.Linear(hidden_channels, hidden_channels),
            torch.nn.BatchNorm1d(hidden_channels),
            torch.nn.ReLU(),
            torch.nn.Linear(hidden_channels, hidden_channels)
        )
        self.conv2 = GINConv(self.mlp2)
        self.lin = torch.nn.Linear(hidden_channels * 2, 2)

    def forward(self, x, edge_index, batch):
        edge_index, _ = dropout_edge(edge_index, p=0.2, training=self.training)
        x1 = self.conv1(x, edge_index)
        x1 = F.relu(x1)
        x1 = F.dropout(x1, p=0.5, training=self.training)
        x2 = self.conv2(x1, edge_index)
        x2 = F.relu(x2)
        x2 = F.dropout(x2, p=0.5, training=self.training)
        x_jk = torch.cat([x1, x2], dim=-1)
        x_pool = global_add_pool(x_jk, batch)
        return self.lin(x_pool)

device = torch.device('cpu')
model = FlakyGreedyGIN(hidden_channels=HIDDEN_CHANNELS).to(device)
optimizer = torch.optim.Adam(model.parameters(), lr=LEARNING_RATE)

# --- 5. CRITICAL CHANGE: WEIGHTED CROSS ENTROPY ---
criterion = torch.nn.CrossEntropyLoss(weight=class_weights.to(device))

print("\nStarting Training (Imbalanced Weighting Strategy)...")
def train():
    model.train()
    total_loss = 0
    for data in train_loader:
        data = data.to(device)
        optimizer.zero_grad()
        out = model(data.x, data.edge_index, data.batch)
        loss = criterion(out, data.y.long())
        loss.backward()
        optimizer.step()
        total_loss += loss.item()
    return total_loss / len(train_loader)

for epoch in range(1, EPOCHS + 1):
    loss = train()
    if epoch % 5 == 0:
        print(f'Epoch: {epoch:03d}, Loss: {loss:.4f}')

# --- 6. SAVE ---
save_path = "model_imbalanced.pth"
torch.save(model.state_dict(), save_path)
print(f"\nTraining Complete. Saved as {save_path}")