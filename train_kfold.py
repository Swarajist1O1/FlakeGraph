import torch
import torch.nn as nn
import torch.nn.functional as F
from torch_geometric.nn import GATConv, global_mean_pool
from sklearn.model_selection import StratifiedKFold
from sklearn.metrics import precision_recall_fscore_support, roc_auc_score
from torch_geometric.loader import DataLoader
from torch.utils.data import WeightedRandomSampler
import glob
import os
import numpy as np
from torch_geometric.utils import dropout_edge

# --- CONFIGURATION ---
DATA_DIR = "processed_tensors/train"  # The 80% Training Zone
K_FOLDS = 5
EPOCHS = 40
BATCH_SIZE = 32
THRESHOLD = 0.66


# --- MODEL DEFINITION ---
from torch_geometric.nn import GINConv, global_add_pool # <--- Note: global_add_pool

import torch
import torch.nn.functional as F
from torch_geometric.nn import GINConv, global_add_pool
from torch_geometric.utils import dropout_edge  # <-- NEW IMPORT

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
    
# --- LOAD DATA ---
print(f"📥 Loading training data from {DATA_DIR}...")
all_files = glob.glob(os.path.join(DATA_DIR, "*.pt"))
data_list = []
labels = []
for f in all_files:
    try:
        d = torch.load(f, weights_only=False)
        data_list.append(d)
        labels.append(int(d.y.item()))
    except: pass

print(f"   Loaded {len(data_list)} files for K-Fold Training.")
device = torch.device('cpu')

# --- START K-FOLD ---
skf = StratifiedKFold(n_splits=K_FOLDS, shuffle=True, random_state=42)
# The way that actually works
criterion = torch.nn.CrossEntropyLoss()

print(f"\n🚀 STARTING MASTER K-FOLD (With Focal Loss)")
print("=" * 60)

for fold, (train_idx, val_idx) in enumerate(skf.split(data_list, labels)):
    print(f"\n🔄 FOLD {fold+1}/{K_FOLDS}")
    
    # Prepare Fold Data
    train_data = [data_list[i] for i in train_idx]
    val_data = [data_list[i] for i in val_idx]
    
    # Sampler for Training Data Only
    train_labels = [labels[i] for i in train_idx]
    class_counts = [train_labels.count(0), train_labels.count(1)]
    weights = [1.0 / c for c in class_counts]
    sample_weights = [weights[l] for l in train_labels]
    sampler = WeightedRandomSampler(sample_weights, len(sample_weights))
    
    train_loader = DataLoader(train_data, batch_size=BATCH_SIZE, sampler=sampler)
    
    # Initialize Model
    model = FlakyGreedyGIN(hidden_channels=64).to(device)
    optimizer = torch.optim.Adam(model.parameters(), lr=0.001, weight_decay=5e-4)
    
    # Train Loop
    model.train()
    for epoch in range(EPOCHS):
        for batch in train_loader:
            batch = batch.to(device)
            optimizer.zero_grad()
            out = model(batch.x, batch.edge_index, batch.batch)
            loss = criterion(out, batch.y.long()) # Focal Loss handles the magic
            loss.backward()
            optimizer.step()
            
    # Save Model
    save_path = f"model_fold_{fold+1}.pth"
    torch.save(model.state_dict(), save_path)
    print(f"   💾 Model saved: {save_path}")

print("\n✅ Training Complete. 5 Models Ready.")