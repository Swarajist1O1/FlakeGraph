class DummyClass_96869 {
  @Test
  public void testSettersCreatedByDefault() throws IOException {
    SpecificCompiler compiler = createCompiler();
    assertTrue(compiler.isCreateSetters());
    compiler.compileToDestination(this.src, this.OUTPUT_DIR.getRoot());
    assertTrue(this.outputFile.exists());
    int foundSetters = 0;
    try(BufferedReader reader = new BufferedReader(new FileReader(this.outputFile))) {
      String line;
      while ((line = reader.readLine()) != null) {
        // We should find the setter in the main class
        line = line.trim();
        if (line.startsWith("public void setValue(")) {
          foundSetters++;
        }

}