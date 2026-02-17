import os
import glob
import random
import torch
import torch.nn.functional as F
from torch_geometric.loader import DataLoader
from torch_geometric.nn import GATConv, global_mean_pool
from tqdm import tqdm
from collections import Counter
from sklearn.metrics import confusion_matrix, classification_report

# --- CONFIGURATION ---
DATA_DIR = "processed_tensors/train"
BATCH_SIZE = 16
HIDDEN_CHANNELS = 64
EPOCHS = 50   # Shortened to 50 for quick check
LEARNING_RATE = 0.0005 # Slower learning rate for stability

# 1. Load Data
print(f"📂 Looking for tensors in: {os.path.abspath(DATA_DIR)}")
files = glob.glob(os.path.join(DATA_DIR, "*.pt"))
dataset = []

if not files: exit()

random.shuffle(files)

# Track labels for weights
all_labels = []
for file in tqdm(files, desc="Loading Data"):
    try:
        data = torch.load(file, weights_only=False)
        dataset.append(data)
        all_labels.append(int(data.y.item()))
    except: pass

# 2. Weights
label_counts = Counter(all_labels)
# Less aggressive weighting to stop it from spamming "1"
weight_0 = 1.0 
weight_1 = 1.5 # Only 1.5x penalty for missing Flaky (was higher before)
class_weights = torch.tensor([weight_0, weight_1])
print(f"⚖️  Weights: Safe={weight_0}, Flaky={weight_1}")

# 3. Split
train_size = int(0.8 * len(dataset))
test_size = len(dataset) - train_size
train_dataset, test_dataset = torch.utils.data.random_split(dataset, [train_size, test_size])

train_loader = DataLoader(train_dataset, batch_size=BATCH_SIZE, shuffle=True)
test_loader = DataLoader(test_dataset, batch_size=BATCH_SIZE, shuffle=False)

# 4. Model
class FlakyGAT(torch.nn.Module):
    def __init__(self, hidden_channels):
        super(FlakyGAT, self).__init__()
        self.conv1 = GATConv(768, hidden_channels)
        self.conv2 = GATConv(hidden_channels, hidden_channels)
        self.lin = torch.nn.Linear(hidden_channels, 2)

    def forward(self, x, edge_index, batch):
        x = self.conv1(x, edge_index)
        x = F.relu(x)
        x = F.dropout(x, p=0.3, training=self.training)
        x = self.conv2(x, edge_index)
        x = F.relu(x)
        x = global_mean_pool(x, batch)
        x = self.lin(x)
        return x

device = torch.device('cpu')
model = FlakyGAT(hidden_channels=HIDDEN_CHANNELS).to(device)
optimizer = torch.optim.Adam(model.parameters(), lr=LEARNING_RATE)
class_weights = class_weights.to(device)
criterion = torch.nn.CrossEntropyLoss(weight=class_weights)

# 5. Train
def train():
    model.train()
    total_loss = 0
    for data in train_loader:
        data = data.to(device)
        optimizer.zero_grad()
        out = model(data.x, data.edge_index, data.batch)
        loss = criterion(out, data.y.long())
        loss.backward()
        optimizer.step()
        total_loss += loss.item()
    return total_loss / len(train_loader)

def test(loader):
    model.eval()
    all_preds = []
    all_labels = []
    
    for data in loader:
        data = data.to(device)
        out = model(data.x, data.edge_index, data.batch)
        pred = out.argmax(dim=1)
        
        all_preds.extend(pred.tolist())
        all_labels.extend(data.y.tolist())

    return all_preds, all_labels

print("\nTraining...")
for epoch in range(1, EPOCHS + 1):
    loss = train()
    if epoch % 10 == 0:
        print(f'Epoch: {epoch:03d}, Loss: {loss:.4f}')

# Final Report
print("\nFINAL RESULTS")
preds, actuals = test(test_loader)

print(classification_report(actuals, preds, target_names=['Safe', 'Flaky']))
print("Confusion Matrix:")
print(confusion_matrix(actuals, preds))

# ... (after the confusion matrix print)

# SAVE THE BRAIN
torch.save(model.state_dict(), "flaky_detector.pth")
print("\nModel saved to 'flaky_detector.pth'")