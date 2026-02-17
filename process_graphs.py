import os
import glob
import re
import torch
import networkx as nx
from torch_geometric.data import Data
from transformers import AutoTokenizer, AutoModel
from tqdm import tqdm

# --- CONFIGURATION ---
INPUT_DIR = "graphs_output"
OUTPUT_DIR = "processed_tensors"

print("⏳ Loading CodeBERT...")
tokenizer = AutoTokenizer.from_pretrained("microsoft/codebert-base")
model = AutoModel.from_pretrained("microsoft/codebert-base")
device = torch.device("cpu") # Keep CPU for safety
model = model.to(device)

def parse_dot_manually(filepath):
    """
    Parses a simple DOT file from Joern without using pydot/GraphViz.
    """
    G = nx.DiGraph()
    
    with open(filepath, 'r', encoding='utf-8') as f:
        lines = f.readlines()
        
    for line in lines:
        line = line.strip()
        if not line or line.startswith("digraph") or line == "}":
            continue
            
        # Match Nodes:  "123" [label = "x = 5"];
        # We look for lines that have [label = "..."]
        node_match = re.search(r'"(\d+)"\s*\[\s*label\s*=\s*"(.*)"\s*\]', line)
        if node_match:
            node_id = node_match.group(1)
            raw_content = node_match.group(2)
            # Clean up escape characters like \" or \n
            clean_content = raw_content.replace('\\"', '"').replace('\\n', ' ')
            G.add_node(node_id, label=clean_content)
            continue
            
        # Match Edges: "123" -> "456"
        edge_match = re.search(r'"(\d+)"\s*->\s*"(\d+)"', line)
        if edge_match:
            src = edge_match.group(1)
            dst = edge_match.group(2)
            G.add_edge(src, dst)
            
    return G

def dot_to_tensor(dot_path, label):
    try:
        # A. Load DOT file (Using our custom manual parser)
        G = parse_dot_manually(dot_path)

        if len(G.nodes()) == 0:
            return None

        # B. Convert Nodes
        node_features = []
        # Create a mapping from string IDs ("123") to 0, 1, 2...
        mapping = {node: i for i, node in enumerate(G.nodes())}

        for node_id in G.nodes():
            node_data = G.nodes[node_id]
            raw_text = node_data.get('label', "statement")
            
            if len(raw_text) < 2: 
                raw_text = "statement"

            inputs = tokenizer(raw_text, return_tensors="pt", truncation=True, max_length=128).to(device)
            with torch.no_grad():
                outputs = model(**inputs)
                embedding = outputs.last_hidden_state.mean(dim=1).squeeze().cpu()
            
            node_features.append(embedding)

        x = torch.stack(node_features)

        # C. Convert Edges
        edge_index = []
        for src, dst in G.edges():
            if src in mapping and dst in mapping:
                edge_index.append([mapping[src], mapping[dst]])
        
        if not edge_index:
             edge_index = torch.empty((2, 0), dtype=torch.long)
        else:
             edge_index = torch.tensor(edge_index, dtype=torch.long).t().contiguous()

        data = Data(x=x, edge_index=edge_index, y=torch.tensor([label], dtype=torch.float))
        return data

    except Exception as e:
        print(f"❌ Error processing {dot_path}: {e}")
        return None

def main():
    os.makedirs(os.path.join(OUTPUT_DIR, "train"), exist_ok=True)

    flaky_graphs = glob.glob(os.path.join(INPUT_DIR, "flaky", "*.dot"))
    non_flaky_graphs = glob.glob(os.path.join(INPUT_DIR, "non_flaky", "*.dot"))
    
    print(f"🚀 Processing {len(flaky_graphs)} Flaky and {len(non_flaky_graphs)} Non-Flaky graphs...")

    count = 0
    # Process Flaky
    for graph_file in tqdm(flaky_graphs, desc="Processing Flaky"):
        data = dot_to_tensor(graph_file, label=1)
        if data:
            name = os.path.basename(graph_file).replace('.dot', '.pt')
            torch.save(data, os.path.join(OUTPUT_DIR, "train", f"flaky_{name}"))
            count += 1

    # Process Non-Flaky
    for graph_file in tqdm(non_flaky_graphs, desc="Processing Non-Flaky"):
        data = dot_to_tensor(graph_file, label=0)
        if data:
            name = os.path.basename(graph_file).replace('.dot', '.pt')
            torch.save(data, os.path.join(OUTPUT_DIR, "train", f"safe_{name}"))
            count += 1

    print(f"\n✅ SUCCESS! Saved {count} tensor files to 'processed_tensors/train/'")
    print("👉 Now run: python train_model.py")

if __name__ == "__main__":
    main()