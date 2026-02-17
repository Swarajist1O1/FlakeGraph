class DummyClass_175778 {
  @Test
  public void testValidate_relativePathWithAppYaml() throws IOException {
    createAppYamlFile(basePath + "/some/directory", "runtime: java");

    when(appYamlPath.getValue()).thenReturn("some/directory/app.yaml");
    IStatus result = pathValidator.validate();
    assertTrue(result.isOK());
  }

}