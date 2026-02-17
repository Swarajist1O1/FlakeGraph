class DummyClass_175780 {
  @Test
  public void testValidateRuntime_javaRuntime() throws IOException {
    File appYaml = createAppYamlFile(tempFolder.getRoot().toString(), "runtime: java");
    IStatus result = AppYamlValidator.validateRuntime(appYaml);
    assertTrue(result.isOK());
  }

}