class DummyClass_78312 {
  @Test
  public void testOnTimerCalled() {
    WindowFn<?, GlobalWindow> windowFn = new GlobalWindows();
    DoFnWithTimers<GlobalWindow> fn = new DoFnWithTimers(windowFn.windowCoder());
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
            WindowingStrategy.of(windowFn));

    Instant currentTime = new Instant(42);
    Duration offset = Duration.millis(37);

    // Mocking is not easily compatible with annotation analysis, so we manually record
    // the method call.
    runner.onTimer(
        DoFnWithTimers.TIMER_ID,
        GlobalWindow.INSTANCE,
        currentTime.plus(offset),
        TimeDomain.EVENT_TIME);

    assertThat(
        fn.onTimerInvocations,
        contains(
            TimerData.of(
                DoFnWithTimers.TIMER_ID,
                StateNamespaces.window(windowFn.windowCoder(), GlobalWindow.INSTANCE),
                currentTime.plus(offset),
                TimeDomain.EVENT_TIME)));
  }

}