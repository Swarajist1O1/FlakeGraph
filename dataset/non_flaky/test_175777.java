class DummyClass_175777 {
  @Test
  public void testValidate_absolutePathNotFile() {
    createAppYamlAsDirectory(basePath);

    String absolutePath = basePath + "/app.yaml";
    when(appYamlPath.getValue()).thenReturn(absolutePath);

    IStatus result = pathValidator.validate();
    assertEquals(IStatus.ERROR, result.getSeverity());
    assertEquals("Not a file: " + new Path(basePath + "/app.yaml").toOSString(),
        result.getMessage());
  }

}