import torch
import os
import networkx as nx
import re
from torch_geometric.data import Data
from transformers import AutoTokenizer, AutoModel
from torch_geometric.nn import GATConv, global_mean_pool
import torch.nn.functional as F

# --- CONFIGURATION ---
MODEL_PATH = "flaky_detector.pth"
HIDDEN_CHANNELS = 64

# 1. Define the Architecture (Must match training!)
class FlakyGAT(torch.nn.Module):
    def __init__(self, hidden_channels):
        super(FlakyGAT, self).__init__()
        self.conv1 = GATConv(768, hidden_channels)
        self.conv2 = GATConv(hidden_channels, hidden_channels)
        self.lin = torch.nn.Linear(hidden_channels, 2)

    def forward(self, x, edge_index, batch):
        x = self.conv1(x, edge_index)
        x = F.relu(x)
        x = self.conv2(x, edge_index)
        x = F.relu(x)
        x = global_mean_pool(x, batch)
        x = self.lin(x)
        return x

# 2. Load the Brain
print("⏳ Loading Model...")
device = torch.device('cpu')
model = FlakyGAT(HIDDEN_CHANNELS).to(device)
model.load_state_dict(torch.load(MODEL_PATH, weights_only=False))
model.eval()
print("✅ Model Loaded!")

# 3. Load CodeBERT (For translation)
tokenizer = AutoTokenizer.from_pretrained("microsoft/codebert-base")
bert_model = AutoModel.from_pretrained("microsoft/codebert-base").to(device)

def predict_single_file(dot_file):
    # A. Manual Parsing (Same as before)
    G = nx.DiGraph()
    with open(dot_file, 'r') as f:
        for line in f:
            node_match = re.search(r'"(\d+)"\s*\[\s*label\s*=\s*"(.*)"\s*\]', line)
            if node_match:
                G.add_node(node_match.group(1), label=node_match.group(2).replace('\\"', '"'))
            edge_match = re.search(r'"(\d+)"\s*->\s*"(\d+)"', line)
            if edge_match:
                G.add_edge(edge_match.group(1), edge_match.group(2))

    if len(G.nodes()) == 0: return "Error: Empty Graph"

    # B. Vectorize
    node_features = []
    mapping = {node: i for i, node in enumerate(G.nodes())}
    
    for node_id in G.nodes():
        text = G.nodes[node_id].get('label', "stmt")
        inputs = tokenizer(text, return_tensors="pt", truncation=True, max_length=128).to(device)
        with torch.no_grad():
            emb = bert_model(**inputs).last_hidden_state.mean(dim=1).squeeze()
        node_features.append(emb)

    x = torch.stack(node_features)
    edge_index = torch.tensor([[mapping[src], mapping[dst]] for src, dst in G.edges()], dtype=torch.long).t().contiguous()
    
    # C. Predict
    data = Data(x=x, edge_index=edge_index).to(device)
    # Batch must be all zeros for a single graph
    data.batch = torch.zeros(x.size(0), dtype=torch.long).to(device)
    
    with torch.no_grad():
        out = model(data.x, data.edge_index, data.batch)
        probs = F.softmax(out, dim=1)
        pred = out.argmax(dim=1).item()
        confidence = probs[0][pred].item()

    return "FLAKY ⚠️" if pred == 1 else "Safe ✅", confidence

# --- TEST ON A FILE ---
# Pick a random file from your output folder to test
test_file = "graphs_output/flaky/test_1.dot" 

if os.path.exists(test_file):
    result, conf = predict_single_file(test_file)
    print(f"\n🔎 Result for {test_file}:")
    print(f"   Prediction: {result}")
    print(f"   Confidence: {conf:.2%}")
else:
    print(f"\n❌ Could not find {test_file}. Please update the path in the script.")