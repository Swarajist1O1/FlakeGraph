import os
import glob
import random
import torch
import torch.nn.functional as F
from torch_geometric.loader import DataLoader
from torch_geometric.nn import GATConv, global_mean_pool
from tqdm import tqdm

# --- CONFIGURATION ---
DATA_DIR = "processed_tensors/train"
BATCH_SIZE = 16
HIDDEN_CHANNELS = 64
EPOCHS = 20
LEARNING_RATE = 0.001

# 1. Load the Dataset
print(f"📂 Looking for tensors in: {os.path.abspath(DATA_DIR)}")
files = glob.glob(os.path.join(DATA_DIR, "*.pt"))
dataset = []

if not files:
    print("❌ No data found! Check 'processed_tensors/train'.")
    exit()

print(f"   Found {len(files)} files. Loading...")
random.shuffle(files)

for file in tqdm(files, desc="Loading Data"):
    try:
        # FIX 1: specific flag for PyTorch 2.6+ to allow loading custom objects
        data = torch.load(file, weights_only=False)
        dataset.append(data)
    except Exception as e:
        print(f"⚠️ Skipping bad file {file}: {e}")

if len(dataset) == 0:
    print("❌ All files failed to load. Check the error messages above.")
    exit()

# 2. Split Train/Test (80% Train, 20% Test)
train_size = int(0.8 * len(dataset))
test_size = len(dataset) - train_size

# FIX 2: Added the parentheses and arguments correctly
train_dataset, test_dataset = torch.utils.data.random_split(dataset, [train_size, test_size])

print(f"📊 Training on {len(train_dataset)} graphs. Testing on {len(test_dataset)} graphs.")

train_loader = DataLoader(train_dataset, batch_size=BATCH_SIZE, shuffle=True)
test_loader = DataLoader(test_dataset, batch_size=BATCH_SIZE, shuffle=False)

# 3. Define the Model (GAT)
class FlakyGAT(torch.nn.Module):
    def __init__(self, hidden_channels):
        super(FlakyGAT, self).__init__()
        self.conv1 = GATConv(768, hidden_channels)
        self.conv2 = GATConv(hidden_channels, hidden_channels)
        self.lin = torch.nn.Linear(hidden_channels, 2)

    def forward(self, x, edge_index, batch):
        x = self.conv1(x, edge_index)
        x = F.relu(x)
        x = F.dropout(x, p=0.5, training=self.training)
        x = self.conv2(x, edge_index)
        x = F.relu(x)
        x = global_mean_pool(x, batch)
        x = F.dropout(x, p=0.5, training=self.training)
        x = self.lin(x)
        return x

# Initialize
device = torch.device('cpu')
model = FlakyGAT(hidden_channels=HIDDEN_CHANNELS).to(device)
optimizer = torch.optim.Adam(model.parameters(), lr=LEARNING_RATE)
criterion = torch.nn.CrossEntropyLoss()

# 4. Training Loop
print("\n🚀 Starting Training...")

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
    correct = 0
    for data in loader:
        data = data.to(device)
        out = model(data.x, data.edge_index, data.batch)
        pred = out.argmax(dim=1)
        correct += int((pred == data.y).sum())
    return correct / len(loader.dataset)

for epoch in range(1, EPOCHS + 1):
    loss = train()
    acc = test(test_loader)
    print(f'Epoch: {epoch:03d}, Loss: {loss:.4f}, Test Accuracy: {acc:.4f}')

print("✅ Training Complete!")