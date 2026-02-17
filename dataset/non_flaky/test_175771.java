class DummyClass_175771 {
  @Test
  public void testContructor_nonAbsoluteBasePath() {
    try {
      when(appYamlPath.getValue()).thenReturn("app.yaml");
      new AppYamlValidator(new Path("non/absolute/base/path"), appYamlPath);
      fail();
    } catch (IllegalArgumentException ex) {
      assertEquals("basePath is not absolute.", ex.getMessage());
    }
  }

}