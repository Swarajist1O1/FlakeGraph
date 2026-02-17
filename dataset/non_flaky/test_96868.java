class DummyClass_96868 {
  @Test
  public void testPrivateFieldVisibility() throws IOException {
    SpecificCompiler compiler = createCompiler();
    compiler.setFieldVisibility(SpecificCompiler.FieldVisibility.PRIVATE);
    assertFalse(compiler.deprecatedFields());
    assertFalse(compiler.publicFields());
    assertTrue(compiler.privateFields());
    compiler.compileToDestination(this.src, this.OUTPUT_DIR.getRoot());
    assertTrue(this.outputFile.exists());
    BufferedReader reader = new BufferedReader(new FileReader(this.outputFile));
    String line = null;
    while ((line = reader.readLine()) != null) {
      // No line, once trimmed, should start with a public field declaration
      // or with a deprecated public field declaration
      line = line.trim();
      assertFalse("Line started with a public field declaration: " + line,
        line.startsWith("public int value"));
      assertFalse("Line started with a deprecated field declaration: " + line,
        line.startsWith("@Deprecated public int value"));
    }
    reader.close();
  }

}