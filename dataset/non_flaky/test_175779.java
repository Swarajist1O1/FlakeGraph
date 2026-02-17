class DummyClass_175779 {
  @Test
  public void testValidate_absolutePathWithAppYaml() throws IOException {
    File absolutePath = tempFolder.newFolder("another", "folder");
    File appYaml = createAppYamlFile(absolutePath.toString(), "runtime: java");

    when(appYamlPath.getValue()).thenReturn(appYaml.toString());
    IStatus result = pathValidator.validate();
    assertTrue(result.isOK());
  }

}