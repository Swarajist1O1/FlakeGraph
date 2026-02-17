class DummyClass_78308 {
  @Test
  public void testOnTimerExceptionsWrappedAsUserCodeException() {
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

    runner.onTimer(
        ThrowingDoFn.TIMER_ID, GlobalWindow.INSTANCE, new Instant(0), TimeDomain.EVENT_TIME);
  }

}