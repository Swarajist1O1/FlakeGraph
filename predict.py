import torch
import torch.nn.functional as F
from torch_geometric.nn import GATConv, global_mean_pool
import os
import glob
import random

# --- CONFIGURATION ---
MODEL_PATH = "flaky_detector_final.pth" 
DATA_DIR = "processed_tensors/train"  # Where your .pt files are
THRESHOLD = 0.80

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

# --- LOAD MODEL ---
device = torch.device('cpu')
model = FlakyGAT(hidden_channels=64).to(device)

if not os.path.exists(MODEL_PATH):
    print("Model not found.")
    exit()

model.load_state_dict(torch.load(MODEL_PATH, map_location=device))
model.eval()
print("Model Loaded!")

# --- GATHER BALANCED BATCH FROM TENSORS ---
print(f"\nScanning {DATA_DIR} for .pt files...")
all_files = glob.glob(os.path.join(DATA_DIR, "*.pt"))
random.shuffle(all_files)

flaky_tensors = []
safe_tensors = []

# Find 10 of each
for f in all_files:
    if len(flaky_tensors) >= 10 and len(safe_tensors) >= 10:
        break
    try:
        data = torch.load(f, weights_only=False)
        label = int(data.y.item())
        if label == 1 and len(flaky_tensors) < 10:
            flaky_tensors.append(f)
        elif label == 0 and len(safe_tensors) < 10:
            safe_tensors.append(f)
    except:
        pass

print(f"   Found {len(flaky_tensors)} Flaky and {len(safe_tensors)} Safe tensors.")

if len(flaky_tensors) < 5:
    print("Not enough Flaky tensors found. Did process_graphs.py run correctly?")
    exit()

# Combine 5 of each for the test
test_batch = flaky_tensors[:5] + safe_tensors[:5]
random.shuffle(test_batch)

# --- RUN TEST ---
print(f"\n{'FILE ID':<20} | {'TRUE LABEL':<10} | {'PREDICTION':<10} | {'CONFIDENCE':<10} | {'RESULT'}")
print("-" * 80)

correct = 0
for f in test_batch:
    data = torch.load(f, weights_only=False)
    data = data.to(device)
    
    # Truth
    real_label = int(data.y.item())
    truth_str = "FLAKY" if real_label == 1 else "Safe"
    
    # Predict
    with torch.no_grad():
        out = model(data.x, data.edge_index, data.batch)
        probs = F.softmax(out, dim=1)
        prob = probs[0][1].item()
    
    pred_str = "FLAKY" if prob >= THRESHOLD else "Safe"
    
    # Grade
    is_correct = False
    if real_label == 1 and prob >= THRESHOLD:
        is_correct = True
    if real_label == 0 and prob < THRESHOLD:
        is_correct = True
    
    res_icon = "Correct" if is_correct else "WRONG"
    if is_correct:
        correct += 1
    
    fname = os.path.basename(f).replace(".pt", "")
    print(f"{fname:<20} | {truth_str:<10} | {pred_str:<10} | {prob:.1%}     | {res_icon}")

print("-" * 80)
print(f"Accuracy: {correct}/10")
