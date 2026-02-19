import torch
import torch.nn.functional as F
from torch_geometric.nn import GATConv, global_mean_pool
from sklearn.metrics import classification_report, confusion_matrix, roc_auc_score, accuracy_score, precision_recall_fscore_support
import glob
import os
import numpy as np
from torch_geometric.utils import dropout_edge

# --- CONFIGURATION ---
TEST_DIR = "processed_tensors/benchmark_test"  # The Locked Vault
THRESHOLD = 0.20                               # Your Optimized Threshold
NUM_MODELS = 5                                 # Number of Ensemble Models

# --- MODEL DEFINITION (Must Match Training) ---
from torch_geometric.nn import GINConv, global_add_pool

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
        
        self.lin = torch.nn.Linear(hidden_channels * 2, 2)

    def forward(self, x, edge_index, batch):
        edge_index, _ = dropout_edge(edge_index, p=0.2, training=self.training)
        
        x1 = self.conv1(x, edge_index)
        x1 = x1.relu()
        x1 = F.dropout(x1, p=0.5, training=self.training)
        
        x2 = self.conv2(x1, edge_index)
        x2 = x2.relu()
        x2 = F.dropout(x2, p=0.5, training=self.training)
        
        x_jk = torch.cat([x1, x2], dim=-1)
        
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
        print(f"Error: {path} not found. Run 'train_kfold_master.py' first.")
        exit()

# --- 2. RUN BENCHMARK ---
print(f"\nRunning Final Benchmark on LOCKED VAULT ({TEST_DIR})...")
files = glob.glob(os.path.join(TEST_DIR, "*.pt"))

if not files:
    print("No benchmark files found! Did you run 'setup_benchmark.py'?")
    exit()

y_true = []
y_probs_avg = []

total_files = len(files)
for idx, f in enumerate(files):
    if idx % 50 == 0:
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

print(f"   Done! Analyzed {len(y_true)} benchmark files.\n")

# --- 3. CALCULATE ALL METRICS ---
y_pred = [1 if p >= THRESHOLD else 0 for p in y_probs_avg]

tn, fp, fn, tp = confusion_matrix(y_true, y_pred).ravel()

prec, rec, f1, support = precision_recall_fscore_support(y_true, y_pred, zero_division=0)
acc = accuracy_score(y_true, y_pred)
auc = roc_auc_score(y_true, y_probs_avg)

# --- 4. PRINT FINAL REPORT ---
print("="*70)
print(f"FINAL BENCHMARK REPORT (Threshold: {THRESHOLD})")
print("="*70)

print(f"\n1. CONFUSION MATRIX")
print(f"   {'-'*45}")
print(f"   |                  | {'Pred: SAFE (0)':<14} | {'Pred: FLAKY (1)':<14} |")
print(f"   {'-'*45}")
print(f"   | Actual SAFE (0)  | {tn:<14} | {fp:<14} |")
print(f"   | Actual FLAKY (1) | {fn:<14} | {tp:<14} |")
print(f"   {'-'*45}")
print(f"   False Alarms (Type I Error): {fp}")
print(f"   Missed Bugs  (Type II Error): {fn}")

print(f"\n2. DETAILED CLASS METRICS")
print(f"   {'-'*65}")
print(f"   | {'Class':<12} | {'Precision':<10} | {'Recall':<10} | {'F1-Score':<10} | {'Support':<8} |")
print(f"   {'-'*65}")
print(f"   | {'SAFE':<12} | {prec[0]:.4f}     | {rec[0]:.4f}     | {f1[0]:.4f}     | {support[0]:<8} |")
print(f"   | {'FLAKY':<12} | {prec[1]:.4f}     | {rec[1]:.4f}     | {f1[1]:.4f}     | {support[1]:<8} |")
print(f"   {'-'*65}")

print(f"\n3. GLOBAL AVERAGES & SCORE")
print(f"   {'-'*65}")
print(f"   | {'Metric':<25} | {'Score':<10} | {'Interpretation':<22} |")
print(f"   {'-'*65}")
print(f"   | {'Accuracy':<25} | {acc:.4f}     | (Overall Correctness)  |")
print(f"   | {'Macro Precision':<25} | {np.mean(prec):.4f}     | (Unweighted Avg)       |")
print(f"   | {'Macro Recall':<25} | {np.mean(rec):.4f}     | (Unweighted Avg)       |")
print(f"   | {'Macro F1':<25} | {np.mean(f1):.4f}     | (Balanced View)        |")

print(f"   {'-'*65}")


print("="*70)
