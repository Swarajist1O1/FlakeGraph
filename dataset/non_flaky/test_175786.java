class DummyClass_175786 {
  @Test
  public void testValidateRuntime_ioException() {
    File nonExisting = new File("/non/existing/file");
    IStatus result = AppYamlValidator.validateRuntime(nonExisting);
    assertEquals(IStatus.ERROR, result.getSeverity());
    assertTrue(result.getMessage().startsWith("Cannot read app.yaml:"));
  }

}