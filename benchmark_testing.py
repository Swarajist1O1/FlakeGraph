import torch
import torch.nn.functional as F
from torch_geometric.nn import GINConv, global_add_pool
from torch_geometric.utils import dropout_edge
from sklearn.metrics import confusion_matrix, roc_auc_score, precision_recall_fscore_support
import glob
import os
import numpy as np

# --- CONFIGURATION ---
TEST_DIR = "processed_tensors/benchmark_test"  # The Vault
THRESHOLD = 0.50                               # Standard threshold for comparison
HIDDEN_CHANNELS = 128                          # MUST match your training scripts

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
        # DropEdge is usually training-only; it will be inactive during .eval() 
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

def judge_model(model_path, model_name):
    print("\n" + "=" * 70)
    print(f"JUDGING MODEL: {model_name}")
    print(f"File: {model_path}")
    print("=" * 70)
    
    device = torch.device('cpu')
    
    # FIX: Corrected hidden_channels to match the saved weights (128)
    model = FlakyGreedyGIN(hidden_channels=HIDDEN_CHANNELS).to(device)

    if not os.path.exists(model_path):
        print(f"Error: Model file '{model_path}' not found.")
        return

    try:
        # Weights_only=False is used for maximum compatibility with saved GNN states
        model.load_state_dict(torch.load(model_path, map_location=device))
        print("   Weights loaded successfully.")
    except Exception as e:
        print(f"   Error: Could not load weights. Architecture mismatch.")
        print(f"   Details: {e}")
        return
        
    model.eval()

    # --- LOAD TEST DATA ---
    files = glob.glob(os.path.join(TEST_DIR, "*.pt"))
    if not files:
        print(f"Error: No files found in {TEST_DIR}.")
        return

    y_true = []
    y_probs = []

    for idx, f in enumerate(files):
        if idx % 100 == 0:
            print(f"   Testing file {idx}/{len(files)}...", end='\r')
        try:
            data = torch.load(f, weights_only=False).to(device)
            y_true.append(int(data.y.item()))
            with torch.no_grad():
                out = model(data.x, data.edge_index, data.batch)
                # Apply softmax to get probability of index 1 (Flaky)
                prob = F.softmax(out, dim=1)[0][1].item()
                y_probs.append(prob)
        except:
            pass
    print(f"   Done! Analyzed {len(y_true)} benchmark files.\n")

    # --- CALCULATE METRICS ---
    y_pred = [1 if p >= THRESHOLD else 0 for p in y_probs]
    
    # Confusion Matrix
    tn, fp, fn, tp = confusion_matrix(y_true, y_pred).ravel()
    
    # Detailed Scores
    prec, rec, f1, support = precision_recall_fscore_support(y_true, y_pred, zero_division=0)
    try:
        auc = roc_auc_score(y_true, y_probs)
    except:
        auc = 0.5

    # --- PRINT REPORT ---
    print(f"1. CONFUSION MATRIX (Threshold: {THRESHOLD})")
    print(f"   {'-' * 45}")
    print(f"   |                  | {'Pred: SAFE (0)':<14} | {'Pred: FLAKY (1)':<14} |")
    print(f"   {'-' * 45}")
    print(f"   | Actual SAFE (0)  | {tn:<14} | {fp:<14} |")
    print(f"   | Actual FLAKY (1) | {fn:<14} | {tp:<14} |")
    print(f"   {'-' * 45}")
    print(f"   False Alarms: {fp}")
    print(f"   Bugs Caught:  {tp} (out of {fn + tp})")

    print(f"\n2. CLASS METRICS")
    print(f"   {'-' * 65}")
    print(f"   | {'Class':<12} | {'Precision':<10} | {'Recall':<10} | {'F1-Score':<10} | {'Support':<8} |")
    print(f"   {'-' * 65}")
    print(f"   | {'SAFE':<12} | {prec[0]:.4f}     | {rec[0]:.4f}     | {f1[0]:.4f}     | {support[0]:<8} |")
    print(f"   | {'FLAKY':<12} | {prec[1]:.4f}     | {rec[1]:.4f}     | {f1[1]:.4f}     | {support[1]:<8} |")
    print(f"   {'-' * 65}")

    print(f"\n3. SUMMARY SCORES")
    print(f"   Flaky Recall:   {rec[1]:.1%}")
    print(f"   Precision:      {prec[1]:.1%}")
    print(f"   Macro F1:       {np.mean(f1):.4f}")

# --- RUN THE BENCHMARK ---
judge_model("model_imbalanced.pth", "Method 1: Stratified (1:27 Weight)")
judge_model("model_sampler.pth", "Method 2: Weighted Sampler (1:3 Ratio)")