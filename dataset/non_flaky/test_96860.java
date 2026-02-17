class DummyClass_96860 {
  @Test
  public void testCanReadTemplateFilesOnTheFilesystem() throws IOException {
    SpecificCompiler compiler = createCompiler();
    compiler.compileToDestination(this.src, OUTPUT_DIR.getRoot());
    assertTrue(new File(OUTPUT_DIR.getRoot(),"SimpleRecord.java").exists());
  }

}