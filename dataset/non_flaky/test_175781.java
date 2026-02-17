class DummyClass_175781 {
  @Test
  public void testValidateRuntime_malformedAppYaml() throws IOException {
    File appYaml = createAppYamlFile(tempFolder.getRoot().toString(), ": m a l f o r m e d !");
    IStatus result = AppYamlValidator.validateRuntime(appYaml);
    assertEquals(IStatus.ERROR, result.getSeverity());
    assertEquals("Malformed app.yaml.", result.getMessage());
  }

}