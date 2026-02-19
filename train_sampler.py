import os
import glob
import random
import numpy as np
import torch
import torch.nn.functional as F
from torch_geometric.loader import DataLoader
from torch_geometric.nn import GATConv, global_mean_pool
from torch.utils.data import WeightedRandomSampler
from collections import Counter
from torch_geometric.utils import dropout_edge
from torch_geometric.nn import GINConv, global_add_pool


# --- 1. REPRODUCIBILITY ---
SEED = 42
random.seed(SEED)
np.random.seed(SEED)
torch.manual_seed(SEED)

# --- CONFIGURATION ---
DATA_DIR = "processed_tensors/train"  # Training on 100% of this folder
BATCH_SIZE = 64
HIDDEN_CHANNELS = 64
EPOCHS = 30
LEARNING_RATE = 0.001

print(f"Loading tensors from: {os.path.abspath(DATA_DIR)}")
files = glob.glob(os.path.join(DATA_DIR, "*.pt"))
dataset = []
labels = []

# 2. Load Data
if not files:
    print("No files found!")
    exit()

print("   Loading dataset...")
for file in files:
    try:
        data = torch.load(file, weights_only=False)
        dataset.append(data)
        labels.append(int(data.y.item()))
    except:
        pass

print(f"   Loaded {len(dataset)} files. No internal splitting will occur.")

# 3. WEIGHTED SAMPLER (Applied to 100% of the loaded data)
class_sample_count = Counter(labels)

# Weight = 1 / Count (Rare classes get huge weights, Common classes get tiny weights)
weight_per_class = {
    0: 1.0 / class_sample_count[0],
    1: 1.0 / class_sample_count[1]
}
samples_weight = torch.tensor([weight_per_class[t] for t in labels])

# Create the Sampler
sampler = WeightedRandomSampler(samples_weight, len(samples_weight))

# 4. Loaders (Train only)
train_loader = DataLoader(dataset, batch_size=BATCH_SIZE, sampler=sampler)

print("   Sampler Active: Model will see ~50/50 split in every batch.")

# 5. Model

class FlakyGreedyGIN(torch.nn.Module):
    def __init__(self, hidden_channels=128): 
        super(FlakyGreedyGIN, self).__init__()
        
        # Using torch.nn to match your existing imports
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
        out = self.lin(x_pool)
        
        return out

device = torch.device('cpu')
model = FlakyGreedyGIN(hidden_channels=HIDDEN_CHANNELS).to(device)
optimizer = torch.optim.Adam(model.parameters(), lr=LEARNING_RATE)

# CRITICAL: NO CLASS WEIGHTS HERE! The Sampler does the work.
criterion = torch.nn.CrossEntropyLoss()

# 6. Train
print("\nStarting Training...")
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

# 7. Save Logic
# Renamed to match the evaluation script
save_path = "model_sampler.pth"
torch.save(model.state_dict(), save_path)
print(f"\n✅ Training Complete! Model saved as {save_path}")