import torch
import torch.nn.functional as F
from torch_geometric.nn import GATConv, global_mean_pool
from sklearn.metrics import classification_report, confusion_matrix, roc_auc_score
from sklearn.model_selection import train_test_split
import glob
import os
import numpy as np
import random

# --- CONFIGURATION ---
MODEL_PATH = "flaky_detector_final_90.pth"
DATA_DIR = "processed_tensors/train"
THRESHOLD = 0.70  # Your chosen threshold
SEED = 42         # MUST MATCH the seed used in training (usually 42)

# --- MODEL DEFINITION ---
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

# --- LOAD R
import torch
import torch.nn.functional as F
from torch_geometric.nn import GATConv, global_mean_pool
from sklearn.metrics import classification_report, confusion_matrix, roc_auc_score
from sklearn.model_selection import train_test_split
import glob
import os
import numpy as np
import random

# --- CONFIGURATION ---
MODEL_PATH = "flaky_detector_final_90.pth"
DATA_DIR = "processed_tensors/train"
THRESHOLD = 0.70  # Your chosen threshold
SEED = 42         # MUST MATCH the seed used in training (usually 42)

# --- MODEL DEFINITION ---
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

# --- LOAD RESOURCES ---
device = torch.device('cpu')
model = FlakyGAT(hidden_channels=64).to(device)

if os.path.exists(MODEL_PATH):
    model.load_state_dict(torch.load(MODEL_PATH, map_location=device))
    model.eval()
    print("Model Loaded. Evaluating on UNSEEN DATA only.")
else:
    print("Model not found.")
    exit()

# --- 1. LOAD ALL FILE PATHS ---
print(f"Loading file list from {DATA_DIR}...")
all_files = glob.glob(os.path.join(DATA_DIR, "*.pt"))

# --- 2. RECREATE THE SPLIT ---
# We need to know labels to stratify, so we quickly peek at them
print("   Scanning labels to recreate the split (this takes a moment)...")
labels = []
valid_files = []
for f in all_files:
    try:
        # Just peek at the label without loading the whole graph to memory
        data = torch.load(f, weights_only=False)
        labels.append(int(data.y.item()))
        valid_files.append(f)
    except:
        pass

# Perform the exact same split as training
train_files, test_files, train_labels, test_labels = train_test_split(
    valid_files, labels, test_size=0.2, random_state=SEED, stratify=labels
)

print(f"   Original Data: {len(valid_files)} files")
print(f"   Training Set:  {len(train_files)} (Ignored - Model already knows these)")
print(f"   Test Set:      {len(test_files)} (Evaluating on these!)")

# --- 3. EVALUATE ONLY ON TEST SET ---
y_true = []
y_probs = []

print(f"\nTesting model on {len(test_files)} unseen files...")

for i, f in enumerate(test_files):
    data = torch.load(f, weights_only=False)
    data = data.to(device)
    y_true.append(int(data.y.item()))
    
    with torch.no_grad():
        out = model(data.x, data.edge_index, data.batch)
        probs = F.softmax(out, dim=1)
        y_probs.append(probs[0][1].item())

# --- 4. REPORT ---
y_pred = [1 if p >= THRESHOLD else 0 for p in y_probs]
auc = roc_auc_score(y_true, y_probs)
tn, fp, fn, tp = confusion_matrix(y_true, y_pred).ravel()

print("\n" + "=" * 60)
print(f"HONEST RESULTS (Threshold: {THRESHOLD:.2f})")
print("=" * 60)

print("\n1. CONFUSION MATRIX (Held-out Test Set)")
print(f"   {'-' * 38}")
print(f"   |              | {'Pred: SAFE':<10} | {'Pred: FLAKY':<10} |")
print(f"   {'-' * 38}")
print(f"   | Actual SAFE  | {tn:<10} | {fp:<10} |")
print(f"   | Actual FLAKY | {fn:<10} | {tp:<10} |")
print(f"   {'-' * 38}")

print("\n2. PERFORMANCE SCORES")
print(classification_report(y_true, y_pred, target_names=['Safe', 'Flaky'], digits=4))

print("3. REAL INTELLIGENCE")
print(f"   AUC Score: {auc:.4f}")
print("=" * 60)
