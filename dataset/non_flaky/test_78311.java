class DummyClass_78311 {
  @Test
  public void testFinishBundleExceptionsWrappedAsUserCodeException() {
    ThrowingDoFn fn = new ThrowingDoFn();
    DoFnRunner<String, String> runner =
        new SimpleDoFnRunner<>(
            null,
            fn,
            NullSideInputReader.empty(),
            null,
            null,
            Collections.emptyList(),
            mockStepContext,
            null,
            Collections.emptyMap(),
            WindowingStrategy.of(new GlobalWindows()));

    thrown.expect(UserCodeException.class);
    thrown.expectCause(is(fn.exceptionToThrow));

    runner.finishBundle();
  }

}