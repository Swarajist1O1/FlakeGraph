class DummyClass_78315 {
  @Test
  public void testInfiniteSkew() {
    SkewingDoFn fn = new SkewingDoFn(Duration.millis(Long.MAX_VALUE));
    DoFnRunner<Duration, Duration> runner =
        new SimpleDoFnRunner<>(
            null,
            fn,
            NullSideInputReader.empty(),
            new ListOutputManager(),
            new TupleTag<>(),
            Collections.emptyList(),
            mockStepContext,
            null,
            Collections.emptyMap(),
            WindowingStrategy.of(new GlobalWindows()));

    runner.startBundle();
    runner.processElement(
        WindowedValue.timestampedValueInGlobalWindow(Duration.millis(1L), new Instant(0)));
    runner.processElement(
        WindowedValue.timestampedValueInGlobalWindow(
            Duration.millis(1L), BoundedWindow.TIMESTAMP_MIN_VALUE.plus(Duration.millis(1))));
    runner.processElement(
        WindowedValue.timestampedValueInGlobalWindow(
            // This is the maximum amount a timestamp in beam can move (from the maximum timestamp
            // to the minimum timestamp).
            Duration.millis(BoundedWindow.TIMESTAMP_MAX_VALUE.getMillis())
                .minus(Duration.millis(BoundedWindow.TIMESTAMP_MIN_VALUE.getMillis())),
            BoundedWindow.TIMESTAMP_MAX_VALUE));
  }

}