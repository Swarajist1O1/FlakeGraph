import torch
import torch.nn.functional as F
from torch_geometric.nn import GATConv, global_mean_pool
from sklearn.metrics import precision_recall_fscore_support
from sklearn.model_selection import train_test_split
import glob
import os
import numpy as np

# --- LOAD EVERYTHING ---
MODEL_PATH = "flaky_detector_final_90.pth"
DATA_DIR = "processed_tensors/train"
SEED = 42
device = torch.device('cpu')

# Definition
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

model = FlakyGAT(hidden_channels=64).to(device)
model.load_state_dict(torch.load(MODEL_PATH, map_location=device))
model.eval()

# Recreate Split
all_files = glob.glob(os.path.join(DATA_DIR, "*.pt"))
labels = []
valid_files = []
for f in all_files:
    try:
        data = torch.load(f, weights_only=False)
        labels.append(int(data.y.item()))
        valid_files.append(f)
    except: pass

_, test_files, _, test_labels = train_test_split(
    valid_files, labels, test_size=0.2, random_state=SEED, stratify=labels
)

# Get Probabilities
y_true, y_probs = [], []
print(f"Scanning {len(test_files)} test files...")
for f in test_files:
    data = torch.load(f, weights_only=False).to(device)
    y_true.append(int(data.y.item()))
    with torch.no_grad():
        out = model(data.x, data.edge_index, data.batch)
        y_probs.append(F.softmax(out, dim=1)[0][1].item())

# Find Best Threshold
print(f"\n{'THRESH':<10} | {'RECALL':<10} | {'PRECISION':<10} | {'F1':<10}")
print("-" * 50)
for t in np.arange(0.30, 0.85, 0.05):
    preds = [1 if p >= t else 0 for p in y_probs]
    p, r, f, _ = precision_recall_fscore_support(y_true, preds, average='binary', zero_division=0)
    print(f"{t:.2f}       | {r:.1%}      | {p:.1%}      | {f:.3f}")