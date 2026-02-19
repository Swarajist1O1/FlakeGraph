import torch
import torch.nn.functional as F
from torch_geometric.nn import GATConv, global_mean_pool
from sklearn.metrics import precision_recall_fscore_support, roc_auc_score, confusion_matrix
import glob
import os
import numpy as np

# --- CONFIG ---
TEST_DIR = "processed_tensors/benchmark_test"
NUM_MODELS = 5

# --- MODEL DEF ---
from torch_geometric.nn import GINConv, global_add_pool # <--- Note: global_add_pool

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
        
        # 2. Jumping Knowledge: Combine both layers' features! (This fixes the 64 vs 128 error)
        x_jk = torch.cat([x1, x2], dim=-1) 
        
        # Pool and Classify
        x_pool = global_add_pool(x_jk, batch) 
        out = self.lin(x_pool)
        
        return out
# --- LOAD MODELS ---
device = torch.device('cpu')
models = []
for i in range(1, NUM_MODELS + 1):
    path = f"model_fold_{i}.pth"
    if os.path.exists(path):
        m = FlakyGreedyGIN(hidden_channels=64).to(device)
        m.load_state_dict(torch.load(path, map_location=device))
        m.eval()
        models.append(m)

# --- GET RAW PROBABILITIES ---
print(f"📥 Scanning Benchmark Vault...")
files = glob.glob(os.path.join(TEST_DIR, "*.pt"))
y_true = []
y_probs = []

for idx, f in enumerate(files):
    if idx % 100 == 0: print(f"   Processing {idx}...", end='\r')
    try:
        data = torch.load(f, weights_only=False).to(device)
        y_true.append(int(data.y.item()))
        
        fold_probs = []
        with torch.no_grad():
            for m in models:
                out = m(data.x, data.edge_index, data.batch)
                fold_probs.append(F.softmax(out, dim=1)[0][1].item())
        y_probs.append(np.mean(fold_probs))
    except: pass

# --- SCAN THRESHOLDS ---
print(f"\n{'THRESH':<10} | {'RECALL':<10} | {'PRECISION':<10} | {'FALSE ALARMS':<12}")
print("-" * 55)

best_f1 = 0
best_t = 0

for t in np.arange(0.15, 0.25, 0.01): 
    y_pred = [1 if p >= t else 0 for p in y_probs]
    tn, fp, fn, tp = confusion_matrix(y_true, y_pred).ravel()
    prec, rec, f1, _ = precision_recall_fscore_support(y_true, y_pred, average='binary', zero_division=0)
    
    print(f"{t:.2f}       | {rec:.1%}      | {prec:.1%}      | {fp:<12}")

print("-" * 55)
print(f"AUC Score is constant at: {roc_auc_score(y_true, y_probs):.4f}")