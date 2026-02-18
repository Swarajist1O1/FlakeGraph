import os
import subprocess
import glob
import shutil
from pathlib import Path
from tqdm import tqdm

# --- CONFIGURATION ---
# If this fails, you might need the full path like "/home/swaraj/bin/joern/joern-parse"
JOERN_PARSE = "joern-parse"   
JOERN_EXPORT = "joern-export"

DATASET_DIR = "dataset"
OUTPUT_DIR = "graphs_output"

def check_tools():
    """Verifies that Joern is actually installed and reachable."""
    if not shutil.which(JOERN_PARSE):
        print(f"❌ CRITICAL ERROR: Could not find '{JOERN_PARSE}' in your PATH.")
        print("   Try running: source ~/.bashrc (or wherever you added Joern)")
        return False
    if not shutil.which(JOERN_EXPORT):
        print(f"❌ CRITICAL ERROR: Could not find '{JOERN_EXPORT}'.")
        return False
    print("✅ Joern tools found!")
    return True

def generate_cpg(java_file, output_root):
    base_name = os.path.basename(java_file).replace(".java", "")
    
    # Absolute paths to prevent confusion
    java_file = os.path.abspath(java_file)
    cpg_bin = os.path.abspath(os.path.join(output_root, f"{base_name}.bin"))
    temp_export_dir = os.path.abspath(os.path.join(output_root, f"temp_{base_name}"))
    final_graph_file = os.path.join(output_root, f"{base_name}.dot") 
    
    cmd_parse = [JOERN_PARSE, java_file, "--output", cpg_bin]
    cmd_export = [JOERN_EXPORT, cpg_bin, "--format", "dot", "--out", temp_export_dir]
    
    try:
        # 1. Run Joern Parse
        # Removed timeout, added error capturing
        subprocess.run(
            cmd_parse, 
            stdout=subprocess.DEVNULL, 
            stderr=subprocess.PIPE, 
            check=True
        )
        
        # 2. Run Joern Export
        if not os.path.exists(cpg_bin):
            return "❌ Parse Failed (No BIN file created)"
            
        subprocess.run(
            cmd_export, 
            stdout=subprocess.DEVNULL, 
            stderr=subprocess.PIPE, 
            check=True
        )
        
        # 3. Aggressive Search
        found_dots = list(Path(temp_export_dir).rglob("*.dot"))
        
        if not found_dots:
            return "⚠️ Export Failed (No DOT files found in temp folder)"
            
        # 4. Filter best file (Largest one is usually the logic)
        best_file = max(found_dots, key=os.path.getsize)
        
        shutil.move(str(best_file), final_graph_file)
        
        # Cleanup
        if os.path.exists(cpg_bin): os.remove(cpg_bin)
        if os.path.exists(temp_export_dir): shutil.rmtree(temp_export_dir)
        
        return "✅ Success"

    except subprocess.CalledProcessError as e:
        # This captures if Joern crashed
        error_msg = e.stderr.decode("utf-8").strip() if e.stderr else "Unknown Error"
        return f"💥 Joern Error: {error_msg[:100]}..." # Print first 100 chars
    except Exception as e:
        return f"❌ Python Error: {e}"
    finally:
        # Always cleanup temp bin/folder
        if os.path.exists(cpg_bin): os.remove(cpg_bin)
        if os.path.exists(temp_export_dir): shutil.rmtree(temp_export_dir)

def main():
    if not check_tools():
        return

    # Create folders
    os.makedirs(os.path.join(OUTPUT_DIR, "flaky"), exist_ok=True)
    os.makedirs(os.path.join(OUTPUT_DIR, "non_flaky"), exist_ok=True)
    
    flaky_files = glob.glob(os.path.join(DATASET_DIR, "flaky", "*.java"))
    non_flaky_files = glob.glob(os.path.join(DATASET_DIR, "non_flaky", "*.java"))
    
    print(f"🚀 Processing {len(flaky_files)} Flaky and {len(non_flaky_files)} Non-Flaky files...")
    
    success_count = 0
    fail_count = 0
    
    # Process Flaky
    for i, f in enumerate(tqdm(flaky_files, desc="Flaky Files")):
        res = generate_cpg(f, os.path.join(OUTPUT_DIR, "flaky"))
        if "✅" in res:
            success_count += 1
        else:
            fail_count += 1
            # Print the error for the first 5 failures ONLY
            if fail_count <= 5:
                tqdm.write(f"❌ Failed {os.path.basename(f)}: {res}")

    # Process Non-Flaky (Using all 8000+ this time?)
    # You might want to limit this if your CPU is slow. 
    # Uncomment next line to only do first 1000
    # non_flaky_files = non_flaky_files[:1000] 
    
    for f in tqdm(non_flaky_files, desc="Non-Flaky Files"):
        res = generate_cpg(f, os.path.join(OUTPUT_DIR, "non_flaky"))
        if "✅" in res:
            success_count += 1
        else:
            fail_count += 1
            if fail_count <= 5:
                 tqdm.write(f"❌ Failed {os.path.basename(f)}: {res}")

    print("\n" + "="*40)
    print(f"🎉 DONE!")
    print(f"✅ Successful Conversions: {success_count}")
    print(f"❌ Failures: {fail_count}")
    if success_count + fail_count > 0:
        print(f"📊 Success Rate: {success_count / (success_count + fail_count):.1%}")
    print("="*40)

if __name__ == "__main__":
    main()