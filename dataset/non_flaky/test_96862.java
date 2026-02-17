class DummyClass_96862 {
  @Test
  public void testCreateAllArgsConstructor() throws Exception {
    SpecificCompiler compiler = createCompiler();
    compiler.compileToDestination(this.src, this.OUTPUT_DIR.getRoot());
    assertTrue(this.outputFile.exists());
    boolean foundAllArgsConstructor = false;
    try(BufferedReader reader = new BufferedReader(new FileReader(this.outputFile))) {
      String line;
      while (!foundAllArgsConstructor && (line = reader.readLine()) != null) {
        foundAllArgsConstructor = line.contains("All-args constructor");
      }
    }
    assertTrue(foundAllArgsConstructor);
  }

}