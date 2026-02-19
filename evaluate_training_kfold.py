import torch
import torch.nn.functional as F
from torch_geometric.nn import GINConv, global_add_pool
from sklearn.metrics import classification_report, confusion_matrix, roc_auc_score, accuracy_score, precision_recall_fscore_support
import glob
import os
import numpy as np

# --- CONFIGURATION ---
DATA_DIR = "processed_tensors/train"  # Looking at the TRAINING data
THRESHOLD = 0.50                      # Standard middle threshold
NUM_MODELS = 5

# --- MODEL DEFINITION ---
# IMPORTANT: If you already upgraded to FlakyGreedyGIN, replace this class
# with the upgraded one from the previous message!
from torch_geometric.utils import dropout_edge

class FlakyGreedyGIN(torch.nn.Module):
    def __init__(self, hidden_channels=64):
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
        
        # Classifier accepts Double size due to Jumping Knowledge
        self.lin = torch.nn.Linear(hidden_channels * 2, 2)

    def forward(self, x, edge_index, batch):
        # 1. DropEdge: Randomly drop 20% of edges during training ONLY
        edge_index, _ = dropout_edge(edge_index, p=0.2, training=self.training)
        
        # Layer 1
        x1 = self.conv1(x, edge_index)
        x1 = x1.relu()
        x1 = F.dropout(x1, p=0.5, training=self.training)
        
        # Layer 2
        x2 = self.conv2(x1, edge_index)
        x2 = x2.relu()
        x2 = F.dropout(x2, p=0.5, training=self.training)
        
        # 2. Jumping Knowledge: Combine both layers' features
        x_jk = torch.cat([x1, x2], dim=-1)
        
        # Pool and Classify
        x_pool = global_add_pool(x_jk, batch)
        out = self.lin(x_pool)
        
        return out

# --- 1. LOAD ENSEMBLE MODELS ---
device = torch.device('cpu')
models = []

print(f"Loading {NUM_MODELS} Ensemble Models...")
for i in range(1, NUM_MODELS + 1):
    path = f"model_fold_{i}.pth"
    if os.path.exists(path):
        m = FlakyGreedyGIN(hidden_channels=64).to(device)
        m.load_state_dict(torch.load(path, map_location=device))
        m.eval()
        models.append(m)
    else:
        print(f"Error: {path} not found.")
        exit()

# --- 2. RUN ON TRAINING DATA ---
print(f"\nRunning Evaluation on TRAINING DATA ({DATA_DIR})...")
files = glob.glob(os.path.join(DATA_DIR, "*.pt"))

y_true = []
y_probs_avg = []

total_files = len(files)
for idx, f in enumerate(files):
    if idx % 100 == 0:
        print(f"   Testing file {idx}/{total_files}...", end='\r')
    try:
        data = torch.load(f, weights_only=False).to(device)
        y_true.append(int(data.y.item()))
        
        fold_probs = []
        with torch.no_grad():
            for m in models:
                out = m(data.x, data.edge_index, data.batch)
                prob = F.softmax(out, dim=1)[0][1].item()
                fold_probs.append(prob)
        
        avg_prob = np.mean(fold_probs)
        y_probs_avg.append(avg_prob)
    except:
        pass

print(f"   Done! Analyzed {len(y_true)} training files.\n")

# --- 3. CALCULATE & PRINT REPORT ---
y_pred = [1 if p >= THRESHOLD else 0 for p in y_probs_avg]
tn, fp, fn, tp = confusion_matrix(y_true, y_pred).ravel()
prec, rec, f1, support = precision_recall_fscore_support(y_true, y_pred, zero_division=0)
auc = roc_auc_score(y_true, y_probs_avg)

print("="*60)
print(f"TRAINING DATA CHEAT SHEET (Threshold: {THRESHOLD})")
print("="*60)

print(f"\n1. CONFUSION MATRIX (On data it already knows)")
print(f"   {'-'*45}")
print(f"   |                  | {'Pred: SAFE':<14} | {'Pred: FLAKY':<14} |")
print(f"   {'-'*45}")
print(f"   | Actual SAFE (0)  | {tn:<14} | {fp:<14} |")
print(f"   | Actual FLAKY (1) | {fn:<14} | {tp:<14} |")
print(f"   {'-'*45}")

print(f"\n2. PERFORMANCE SCORES")
print(classification_report(y_true, y_pred, target_names=['Safe', 'Flaky'], digits=4))


print("="*60)