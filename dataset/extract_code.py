import pandas as pd
import os

# 1. Load the dataset
df = pd.read_csv('FlakeBench_dataset.csv')

# 2. Create directories for our raw Java files
os.makedirs('dataset/flaky', exist_ok=True)
os.makedirs('dataset/non_flaky', exist_ok=True)

print(f"Extracting {len(df)} tests...")

# 3. Loop through the dataset and write the code to files
for index, row in df.iterrows():
    test_id = row['id']
    code = row['full_code']
    category = row['category']
    
    # Category 5 is non-flaky. Everything else is flaky.
    if category == 5:
        folder = 'dataset/non_flaky'
    else:
        folder = 'dataset/flaky'
        
    # We wrap the snippet in a dummy class so Joern parses it perfectly as valid Java
    java_content = f"class DummyClass_{test_id} {{\n{code}\n}}"
    
    file_path = os.path.join(folder, f"test_{test_id}.java")
    
    with open(file_path, 'w', encoding='utf-8') as f:
        f.write(java_content)

print("Extraction complete! Your Java files are ready in the 'dataset/' folder.")