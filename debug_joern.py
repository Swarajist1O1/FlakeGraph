import os
import subprocess
import glob
import shutil

# --- CONFIGURATION ---
JOERN_PARSE = "joern-parse"
JOERN_EXPORT = "joern-export"
DATASET_DIR = "dataset"
OUTPUT_DIR = "graphs_output"

def main():
    print("--- STARTING DEBUG SCRIPT ---")

    # 1. Check Input Files
    flaky_path = os.path.join(DATASET_DIR, "flaky", "*.java")
    print(f"1. Looking for Java files in: {flaky_path}")
    
    flaky_files = glob.glob(flaky_path)
    print(f"   Found {len(flaky_files)} files.")
    
    if len(flaky_files) == 0:
        print("❌ ERROR: No files found! Check your 'dataset/flaky' folder.")
        return

    # 2. Pick just ONE file to test
    target_file = flaky_files[0]
    print(f"\n2. Testing on file: {target_file}")
    
    base_name = os.path.basename(target_file).replace(".java", "")
    output_folder = os.path.join(OUTPUT_DIR, "debug_test")
    os.makedirs(output_folder, exist_ok=True)
    
    cpg_bin = os.path.join(output_folder, "cpg.bin")
    export_dir = os.path.join(output_folder, "export_dir")

    # 3. Run Joern Parse
    cmd_parse = [JOERN_PARSE, target_file, "--output", cpg_bin]
    print(f"\n3. Running Parse Command: {' '.join(cmd_parse)}")
    
    # Run and print output to screen so we can see if it hangs
    result_parse = subprocess.run(cmd_parse, capture_output=True, text=True)
    
    if result_parse.returncode != 0:
        print("❌ Parse Failed!")
        print(result_parse.stderr)
        return
    else:
        print("✅ Parse Success!")

    # 4. Run Joern Export
    cmd_export = [JOERN_EXPORT, cpg_bin, "--format", "dot", "--out", export_dir]
    print(f"\n4. Running Export Command: {' '.join(cmd_export)}")
    
    result_export = subprocess.run(cmd_export, capture_output=True, text=True)
    
    if result_export.returncode != 0:
        print("❌ Export Failed!")
        print(result_export.stderr)
        return
    else:
        print("✅ Export Success!")

    # 5. Check Output
    print(f"\n5. Checking folder: {export_dir}")
    if os.path.exists(export_dir):
        files = os.listdir(export_dir)
        print(f"   Files found: {files}")
    else:
        print("❌ Export folder was NOT created.")

if __name__ == "__main__":
    main()