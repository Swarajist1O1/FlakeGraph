import torch
import torch.nn.functional as F
from torch_geometric.nn import GATConv, global_mean_pool
from sklearn.metrics import roc_curve, auc
import matplotlib.pyplot as plt
import glob
import os

# --- CONFIGURATION ---
MODEL_PATH = "flaky_detector_final.pth"
DATA_DIR = "processed_tensors/train"

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
else:
    print("Model not found.")
    exit()

# --- GATHER PROBABILITIES ---
print("Loading data for ROC calculation...")
files = glob.glob(os.path.join(DATA_DIR, "*.pt"))
y_true = []
y_probs = []

for f in files:
    try:
        data = torch.load(f, weights_only=False)
        data = data.to(device)
        y_true.append(int(data.y.item()))
        with torch.no_grad():
            out = model(data.x, data.edge_index, data.batch)
            probs = F.softmax(out, dim=1)
            y_probs.append(probs[0][1].item())
    except:
        pass

# --- CALCULATE ROC ---
fpr, tpr, thresholds = roc_curve(y_true, y_probs)
roc_auc = auc(fpr, tpr)

# --- PLOT ROC CURVE ---
plt.figure(figsize=(8, 6))
plt.plot(
    fpr,
    tpr,
    color='darkorange',
    lw=2,
    label=f'ROC curve (AUC = {roc_auc:.2f})'
)
plt.plot(
    [0, 1],
    [0, 1],
    color='navy',
    lw=2,
    linestyle='--',
    label='Random Guessing'
)
plt.xlim([0.0, 1.0])
plt.ylim([0.0, 1.05])
plt.xlabel('False Positive Rate (False Alarms)')
plt.ylabel('True Positive Rate (Recall)')
plt.title('Receiver Operating Characteristic (ROC)')
plt.legend(loc="lower right")
plt.grid(alpha=0.3)
plt.savefig('roc_curve.png')
print(f"ROC Curve saved as 'roc_curve.png' (AUC Score: {roc_auc:.4f})")
