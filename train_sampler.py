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
from torch.utils.data import WeightedRandomSampler
from collections import Counter

# --- 1. REPRODUCIBILITY ---
SEED = 42
random.seed(SEED)
np.random.seed(SEED)
torch.manual_seed(SEED)

# --- CONFIGURATION ---
DATA_DIR = "processed_tensors/train"
BATCH_SIZE = 64
HIDDEN_CHANNELS = 64
EPOCHS = 90
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

# 3. STRATIFIED SPLIT (Keep the Exam Realistic)
print("   Performing Stratified Split (80/20)...")
train_data, test_data, train_labels, test_labels = train_test_split(
    dataset, labels, test_size=0.20, random_state=SEED, stratify=labels
)
print(f"   Train: {len(train_data)} | Test: {len(test_data)}")

# 4. WEIGHTED SAMPLER (The Magic Trick)
# We calculate weights for every single file in the training set
train_targets = [int(data.y.item()) for data in train_data]
class_sample_count = Counter(train_targets)

# Weight = 1 / Count (Rare classes get huge weights, Common classes get tiny weights)
weight_per_class = {
    0: 1.0 / class_sample_count[0],
    1: 1.0 / class_sample_count[1]
}
samples_weight = torch.tensor([weight_per_class[t] for t in train_targets])

# Create the Sampler
sampler = WeightedRandomSampler(samples_weight, len(samples_weight))

# 5. Loaders (Apply Sampler to Train only)
train_loader = DataLoader(train_data, batch_size=BATCH_SIZE, sampler=sampler)
test_loader = DataLoader(test_data, batch_size=BATCH_SIZE, shuffle=False)

print("   Sampler Active: Model will see ~50/50 split in every batch.")

# 6. Model
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

# CRITICAL: NO CLASS WEIGHTS HERE! The Sampler does the work.
criterion = torch.nn.CrossEntropyLoss()

# 7. Train
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

# 8. Results
print("\nFINAL RESULTS")
preds, actuals = test(test_loader)
print(classification_report(actuals, preds, target_names=['Safe', 'Flaky']))
print("Confusion Matrix:")
print(confusion_matrix(actuals, preds))

# Save Logic
from sklearn.metrics import recall_score
flaky_recall = recall_score(actuals, preds, pos_label=1)
if flaky_recall > 0.60:
    torch.save(model.state_dict(), "flaky_detector_final.pth")
    print("Model Saved!")
