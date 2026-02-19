import torch
import torch.nn.functional as F
from torch_geometric.nn import GINConv, global_add_pool
from torch_geometric.utils import dropout_edge
from sklearn.metrics import confusion_matrix, roc_auc_score, precision_recall_fscore_support
import glob
import os
import numpy as np

# --- CONFIGURATION ---
DATA_DIR = "processed_tensors/train"
# We define both paths to check
MODELS_TO_TEST = {
    "Stratified (1:27 Weight)": "model_imbalanced.pth",
    "Weighted Sampler (1:3 Ratio)": "model_sampler.pth"
}
THRESHOLD = 0.55
HIDDEN_CHANNELS = 128 

# --- MODEL DEFINITION ---
class FlakyGreedyGIN(torch.nn.Module):
    def __init__(self, hidden_channels=128): 
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
        x1 = self.conv1(x, edge_index); x1 = F.relu(x1)
        x1 = F.dropout(x1, p=0.5, training=self.training)
        x2 = self.conv2(x1, edge_index); x2 = F.relu(x2)
        x2 = F.dropout(x2, p=0.5, training=self.training)
        x_jk = torch.cat([x1, x2], dim=-1) 
        x_pool = global_add_pool(x_jk, batch) 
        return self.lin(x_pool)

def evaluate_model(path, name, data_files, device):
    if not os.path.exists(path):
        return None
    
    model = FlakyGreedyGIN(hidden_channels=HIDDEN_CHANNELS).to(device)
    model.load_state_dict(torch.load(path, map_location=device))
    model.eval()
    
    y_true, y_probs = [], []
    for f in data_files:
        try:
            data = torch.load(f, weights_only=False).to(device)
            y_true.append(int(data.y.item()))
            with torch.no_grad():
                out = model(data.x, data.edge_index, data.batch)
                prob = F.softmax(out, dim=1)[0][1].item()
                y_probs.append(prob)
        except: pass
    
    y_pred = [1 if p >= THRESHOLD else 0 for p in y_probs]
    tn, fp, fn, tp = confusion_matrix(y_true, y_pred).ravel()
    prec, rec, f1, _ = precision_recall_fscore_support(y_true, y_pred, zero_division=0)
    auc = roc_auc_score(y_true, y_probs)
    
    return {
        "tn": tn, "fp": fp, "fn": fn, "tp": tp,
        "prec": prec[1], "rec": rec[1], "auc": auc
    }

# --- EXECUTION ---
device = torch.device('cpu')
files = glob.glob(os.path.join(DATA_DIR, "*.pt"))
print(f"Analyzing {len(files)} files against multiple models...\n")

results = {}
for name, path in MODELS_TO_TEST.items():
    print(f"Evaluating {name}...")
    res = evaluate_model(path, name, files, device)
    if res: results[name] = res

# --- FINAL SIDE-BY-SIDE REPORT ---
print("\n" + "=" * 85)
print(f"{'TRAINING DATA SANITY CHECK':^85}")
print("=" * 85)
print(f"{'Model Name':<30} | {'Prec':<8} | {'Rec':<8} | {'AUC':<8} | {'FP (False Alarms)':<15}")
print("-" * 85)

for name, res in results.items():
    print(f"{name:<30} | {res['prec']:.1%}   | {res['rec']:.1%}   | {res['auc']:.4f} | {res['fp']}")

print("=" * 85)