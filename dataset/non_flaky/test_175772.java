class DummyClass_175772 {
  @Test
  public void testValidate_relativePathAndNoAppYaml() {
    when(appYamlPath.getValue()).thenReturn("relative/path/app.yaml");

    IStatus result = pathValidator.validate();
    assertEquals(IStatus.ERROR, result.getSeverity());
    assertEquals("app.yaml does not exist.", result.getMessage());
  }

}