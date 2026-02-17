class DummyClass_106594 {
  @Test
  public void nativeLibPresent() throws Exception {
    File testLibDir = ValidationTestUtils.createTemporaryDirectory();
    String testLibPath = testLibDir.getPath();

    String libPath = testLibPath;
    System.setProperty(NativeLibValidationTask.NATIVE_LIB_PATH, libPath);

    NativeLibValidationTask task = new NativeLibValidationTask();
    ValidationTaskResult result = task.validateImpl(ImmutableMap.of());
    assertEquals(ValidationUtils.State.OK, result.getState());
  }

}