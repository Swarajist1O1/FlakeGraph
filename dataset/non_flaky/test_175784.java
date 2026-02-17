class DummyClass_175784 {
  @Test
  public void testValidateRuntime_notJavaRuntime() throws IOException {
    File appYaml = createAppYamlFile(tempFolder.getRoot().toString(), "runtime: python");
    IStatus result = AppYamlValidator.validateRuntime(appYaml);
    assertEquals(IStatus.ERROR, result.getSeverity());
    assertEquals("\"runtime: python\" in app.yaml is not \"java\".", result.getMessage());
  }

}