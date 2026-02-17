class DummyClass_175783 {
  @Test
  public void testValidateRuntime_nullRuntime() throws IOException {
    File appYaml = createAppYamlFile(tempFolder.getRoot().toString(), "runtime:");
    IStatus result = AppYamlValidator.validateRuntime(appYaml);
    assertEquals(IStatus.ERROR, result.getSeverity());
    assertEquals("\"runtime: null\" in app.yaml is not \"java\".", result.getMessage());
  }

}