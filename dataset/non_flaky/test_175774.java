class DummyClass_175774 {
  @Test
  public void testValidate_relativePathAndInvalidFileName() {
    when(appYamlPath.getValue()).thenReturn("relative/path/my-app.yaml");

    IStatus result = pathValidator.validate();
    assertEquals(IStatus.ERROR, result.getSeverity());
    assertEquals("File name is not app.yaml: "
        + new Path(basePath + "/relative/path/my-app.yaml").toOSString(),
        result.getMessage());
  }

}