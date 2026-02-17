class DummyClass_175773 {
  @Test
  public void testValidate_absolutePathAndNoAppYaml() {
    String absolutePath = basePath + "/sub/directory/app.yaml";
    when(appYamlPath.getValue()).thenReturn(absolutePath);

    IStatus result = pathValidator.validate();
    assertEquals(IStatus.ERROR, result.getSeverity());
    assertEquals("app.yaml does not exist.", result.getMessage());
  }

}