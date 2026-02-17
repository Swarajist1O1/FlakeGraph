class DummyClass_106595 {
  @Test
  public void nativeLibMissing() throws Exception {
    String libPath = "/usr/missing";
    System.setProperty(NativeLibValidationTask.NATIVE_LIB_PATH, libPath);

    NativeLibValidationTask task = new NativeLibValidationTask();
    ValidationTaskResult result = task.validateImpl(ImmutableMap.of());
    assertEquals(ValidationUtils.State.WARNING, result.getState());
    assertThat(result.getResult(), containsString("Java native lib not found at /usr/missing"));
    assertThat(result.getAdvice(), containsString("Please check your path /usr/missing"));
  }

}