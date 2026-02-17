class DummyClass_175776 {
  @Test
  public void testValidate_relativePathNotFile() {
    createAppYamlAsDirectory(basePath);
    when(appYamlPath.getValue()).thenReturn("app.yaml");

    IStatus result = pathValidator.validate();
    assertEquals(IStatus.ERROR, result.getSeverity());
    assertEquals("Not a file: " + new Path(basePath + "/app.yaml").toOSString(),
        result.getMessage());
  }

}