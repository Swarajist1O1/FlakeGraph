class DummyClass_175785 {
  @Test
  public void testValidateRuntime_customRuntime() throws IOException {
    File appYaml = createAppYamlFile(tempFolder.getRoot().toString(), "runtime: custom");
    IStatus result = AppYamlValidator.validateRuntime(appYaml);
    assertEquals(IStatus.ERROR, result.getSeverity());
    assertEquals("\"runtime: custom\" is not yet supported by Cloud Tools for Eclipse.",
        result.getMessage());
  }

}