class DummyClass_96867 {
  @Test
  public void testPublicDeprecatedFieldVisibility() throws IOException {
    SpecificCompiler compiler = createCompiler();
    assertTrue(compiler.deprecatedFields());
    assertTrue(compiler.publicFields());
    assertFalse(compiler.privateFields());
    compiler.compileToDestination(this.src, this.OUTPUT_DIR.getRoot());
    assertTrue(this.outputFile.exists());
    BufferedReader reader = new BufferedReader(new FileReader(this.outputFile));
    String line;
    while ((line = reader.readLine()) != null) {
      // No line, once trimmed, should start with a public field declaration
      line = line.trim();
      assertFalse("Line started with a public field declaration: " + line,
        line.startsWith("public int value"));
    }
    reader.close();
  }

}