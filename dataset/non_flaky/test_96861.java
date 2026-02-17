class DummyClass_96861 {
  @Test
  public void testPublicFieldVisibility() throws IOException {
    SpecificCompiler compiler = createCompiler();
    compiler.setFieldVisibility(SpecificCompiler.FieldVisibility.PUBLIC);
    assertFalse(compiler.deprecatedFields());
    assertTrue(compiler.publicFields());
    assertFalse(compiler.privateFields());
    compiler.compileToDestination(this.src, this.OUTPUT_DIR.getRoot());
    assertTrue(this.outputFile.exists());
    try(BufferedReader reader = new BufferedReader(new FileReader(this.outputFile))) {
      String line;
      while ((line = reader.readLine()) != null) {
        // No line, once trimmed, should start with a deprecated field declaration
        // nor a private field declaration.  Since the nested builder uses private
        // fields, we cannot do the second check.
        line = line.trim();
        assertFalse("Line started with a deprecated field declaration: " + line,
                line.startsWith("@Deprecated public int value"));
      }
    }
  }

}