class DummyClass_78238 {
  @Test
  public void testOnTimerCalled() {
    PushbackSideInputDoFnRunner<Integer, Integer> runner = createRunner(ImmutableList.of());

    String timerId = "fooTimer";
    IntervalWindow window = new IntervalWindow(new Instant(4), new Instant(16));
    Instant timestamp = new Instant(72);

    // Mocking is not easily compatible with annotation analysis, so we manually record
    // the method call.
    runner.onTimer(timerId, window, new Instant(timestamp), TimeDomain.EVENT_TIME);

    assertThat(
        underlying.firedTimers,
        contains(
            TimerData.of(
                timerId,
                StateNamespaces.window(IntervalWindow.getCoder(), window),
                timestamp,
                TimeDomain.EVENT_TIME)));
  }

}