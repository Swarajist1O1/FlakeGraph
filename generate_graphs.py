import os
import subprocess
import glob
import shutil

# --- CONFIGURATION ---
JOERN_PARSE = "joern-parse"
JOERN_EXPORT = "joern-export"
DATASET_DIR = "dataset"
OUTPUT_DIR = "graphs_output"

def generate_cpg(java_file, output_folder):
    base_name = os.path.basename(java_file).replace(".java", "")
    
    # Define paths
    # We use absolute paths to avoid any confusion
    java_file = os.path.abspath(java_file)
    output_folder = os.path.abspath(output_folder)
    
    cpg_bin = os.path.join(output_folder, f"{base_name}.bin")
    temp_export_dir = os.path.join(output_folder, f"temp_{base_name}")
    final_graph_file = os.path.join(output_folder, f"{base_name}.dot") 
    
    # 1. Run Joern Parse
    cmd_parse = [JOERN_PARSE, java_file, "--output", cpg_bin]
    
    # 2. Run Joern Export
    cmd_export = [JOERN_EXPORT, cpg_bin, "--format", "dot", "--out", temp_export_dir]
    
    try:
        # Parse
        subprocess.run(cmd_parse, stdout=subprocess.DEVNULL, stderr=subprocess.DEVNULL, check=True)
        
        # Export
        subprocess.run(cmd_export, stdout=subprocess.DEVNULL, stderr=subprocess.DEVNULL, check=True)
        
        # 3. Find the right file
        # Joern creates files like 0-cpg.dot, 1-cpg.dot, etc.
        dot_files = glob.glob(os.path.join(temp_export_dir, "*.dot"))
        
        if not dot_files:
            print(f"⚠️  No DOT files generated for {base_name}")
            return False
            
        # Pick the LARGEST file. 
        # Why? The small files are usually just "Import" statements or "Class Definitions".
        # The largest file is almost always the actual "Test Method" with the logic we need.
        best_file = max(dot_files, key=os.path.getsize)
        
        # Move it to the final home
        shutil.move(best_file, final_graph_file)
        
        # 4. Cleanup
        if os.path.exists(cpg_bin):
            os.remove(cpg_bin)
        if os.path.exists(temp_export_dir):
            shutil.rmtree(temp_export_dir)
            
        print(f"✅ Generated: {base_name}.dot")
        return True

    except Exception as e:
        print(f"❌ Failed: {base_name} - {e}")
        # Cleanup on fail
        if os.path.exists(cpg_bin): os.remove(cpg_bin)
        if os.path.exists(temp_export_dir): shutil.rmtree(temp_export_dir)
        return False

def main():
    # Create directories
    os.makedirs(os.path.join(OUTPUT_DIR, "flaky"), exist_ok=True)
    os.makedirs(os.path.join(OUTPUT_DIR, "non_flaky"), exist_ok=True)
    
    # Find files
    flaky_files = glob.glob(os.path.join(DATASET_DIR, "flaky", "*.java"))
    non_flaky_files = glob.glob(os.path.join(DATASET_DIR, "non_flaky", "*.java"))
    
    print(f"🚀 Found {len(flaky_files)} flaky and {len(non_flaky_files)} non-flaky tests.")
    print("Starting generation...")
    
    # Process ALL Flaky files
    for f in flaky_files:
        generate_cpg(f, os.path.join(OUTPUT_DIR, "flaky"))
        
    # Process subset of Non-Flaky (e.g. 500) to save time, or remove [:500] to do all
    print("Processing non-flaky...")
    for f in non_flaky_files[:500]:
        generate_cpg(f, os.path.join(OUTPUT_DIR, "non_flaky"))

    print("\n🎉 DONE! Graphs are saved in 'graphs_output/'")

if __name__ == "__main__":
    main()