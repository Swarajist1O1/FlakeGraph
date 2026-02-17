class DummyClass_78314 {
  @Test
  public void testSkew() {
    SkewingDoFn fn = new SkewingDoFn(Duration.standardMinutes(10L));
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
    // Outputting between "now" and "now - allowed skew" succeeds.
    runner.processElement(
        WindowedValue.timestampedValueInGlobalWindow(Duration.standardMinutes(5L), new Instant(0)));
    thrown.expect(UserCodeException.class);
    thrown.expectCause(isA(IllegalArgumentException.class));
    thrown.expectMessage("must be no earlier");
    thrown.expectMessage(
        String.format("timestamp of the current input (%s)", new Instant(0).toString()));
    thrown.expectMessage(
        String.format(
            "the allowed skew (%s)",
            PeriodFormat.getDefault().print(Duration.standardMinutes(10L).toPeriod())));
    // Outputting before "now - allowed skew" fails.
    runner.processElement(
        WindowedValue.timestampedValueInGlobalWindow(Duration.standardHours(1L), new Instant(0)));
  }

}