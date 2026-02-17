class DummyClass_175775 {
  @Test
  public void testValidate_absolutePathInvalidFileName() {
    String absolutePath = basePath + "/sub/directory/my-app.yaml";
    when(appYamlPath.getValue()).thenReturn(absolutePath);

    IStatus result = pathValidator.validate();
    assertEquals(IStatus.ERROR, result.getSeverity());
    assertEquals("File name is not app.yaml: "
        + new Path(basePath + "/sub/directory/my-app.yaml").toOSString(),
        result.getMessage());
  }

}