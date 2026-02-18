import os
import glob
import random
import numpy as np
import torch
import torch.nn.functional as F
from torch_geometric.loader import DataLoader
from torch_geometric.nn import GATConv, global_mean_pool
from sklearn.model_selection import train_test_split
from sklearn.metrics import classification_report, confusion_matrix
from collections import Counter

# --- 1. REPRODUCIBILITY (Lock the Randomness) ---
SEED = 42
random.seed(SEED)
np.random.seed(SEED)
torch.manual_seed(SEED)

# --- CONFIGURATION ---
DATA_DIR = "processed_tensors/train"
BATCH_SIZE = 64
HIDDEN_CHANNELS = 64
EPOCHS = 30
LEARNING_RATE = 0.001

print(f"Loading tensors from: {os.path.abspath(DATA_DIR)}")
files = glob.glob(os.path.join(DATA_DIR, "*.pt"))
dataset = []
labels = []

# 2. Load Data & Extract Labels for Stratification
if not files:
    print("No files found! Run process_graphs.py first.")
    exit()

print("   Loading dataset into memory (this may take a moment)...")
for file in files:
    try:
        data = torch.load(file, weights_only=False)
        dataset.append(data)
        labels.append(int(data.y.item()))  # 0 or 1
    except:
        pass

# 3. STRATIFIED SPLIT (The Fix)
print("   Performing Stratified Split (80/20)...")

train_data, test_data, train_labels, test_labels = train_test_split(
    dataset,
    labels,
    test_size=0.20,
    random_state=SEED,
    stratify=labels
)

print(f"   Train Set: {len(train_data)} files | Test Set: {len(test_data)} files")

# 4. Manual Class Weights
class_weights = torch.tensor([1.0, 100.0])

print(f"   Manual Weights applied: Safe=1.0, Flaky=10.0")

# 5. Dataloaders
train_loader = DataLoader(train_data, batch_size=BATCH_SIZE, shuffle=True)
test_loader = DataLoader(test_data, batch_size=BATCH_SIZE, shuffle=False)

# 6. Model (GAT)
class FlakyGAT(torch.nn.Module):
    def __init__(self, hidden_channels):
        super(FlakyGAT, self).__init__()
        self.conv1 = GATConv(768, hidden_channels)
        self.conv2 = GATConv(hidden_channels, hidden_channels)
        self.lin = torch.nn.Linear(hidden_channels, 2)

    def forward(self, x, edge_index, batch):
        x = self.conv1(x, edge_index)
        x = F.relu(x)
        x = F.dropout(x, p=0.4, training=self.training)
        x = self.conv2(x, edge_index)
        x = F.relu(x)
        x = global_mean_pool(x, batch)
        x = self.lin(x)
        return x

device = torch.device('cpu')
model = FlakyGAT(hidden_channels=HIDDEN_CHANNELS).to(device)
optimizer = torch.optim.Adam(model.parameters(), lr=LEARNING_RATE)
criterion = torch.nn.CrossEntropyLoss(weight=class_weights.to(device))

# 7. Training Loop
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

def test(loader):
    model.eval()
    all_preds = []
    all_labels = []
    for data in loader:
        data = data.to(device)
        out = model(data.x, data.edge_index, data.batch)
        pred = out.argmax(dim=1)
        all_preds.extend(pred.tolist())
        all_labels.extend(data.y.tolist())
    return all_preds, all_labels

for epoch in range(1, EPOCHS + 1):
    loss = train()
    if epoch % 5 == 0:
        print(f'Epoch: {epoch:03d}, Loss: {loss:.4f}')

# 8. Final Report
print("\nFINAL RESULTS")
preds, actuals = test(test_loader)
print(classification_report(actuals, preds, target_names=['Safe', 'Flaky']))
print("Confusion Matrix:")
print(confusion_matrix(actuals, preds))

# Save if good
from sklearn.metrics import recall_score
flaky_recall = recall_score(actuals, preds, pos_label=1)
if flaky_recall > 0.70:
    torch.save(model.state_dict(), "flaky_detector_imbalanced.pth")
    print("Model Saved!")
