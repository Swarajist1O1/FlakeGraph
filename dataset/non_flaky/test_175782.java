class DummyClass_175782 {
  @Test
  public void testValidateRuntime_noRuntime() throws IOException {
    File appYaml = createAppYamlFile(tempFolder.getRoot().toString(), "env: flex");
    IStatus result = AppYamlValidator.validateRuntime(appYaml);
    assertEquals(IStatus.ERROR, result.getSeverity());
    assertEquals("\"runtime: null\" in app.yaml is not \"java\".", result.getMessage());
  }

}