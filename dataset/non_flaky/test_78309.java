class DummyClass_78309 {
  @Test
  public void testTimerSet() {
    WindowFn<?, ?> windowFn = new GlobalWindows();
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
            WindowingStrategy.of(new GlobalWindows()));

    // Setting the timer needs the current time, as it is set relative
    Instant currentTime = new Instant(42);
    when(mockTimerInternals.currentInputWatermarkTime()).thenReturn(currentTime);

    runner.processElement(WindowedValue.valueInGlobalWindow("anyValue"));

    verify(mockTimerInternals)
        .setTimer(
            StateNamespaces.window(new GlobalWindows().windowCoder(), GlobalWindow.INSTANCE),
            DoFnWithTimers.TIMER_ID,
            currentTime.plus(DoFnWithTimers.TIMER_OFFSET),
            TimeDomain.EVENT_TIME);
  }

}