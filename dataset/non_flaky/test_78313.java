class DummyClass_78313 {
  @Test
  public void testBackwardsInTimeNoSkew() {
    SkewingDoFn fn = new SkewingDoFn(Duration.ZERO);
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
    // An element output at the current timestamp is fine.
    runner.processElement(
        WindowedValue.timestampedValueInGlobalWindow(Duration.ZERO, new Instant(0)));
    thrown.expect(UserCodeException.class);
    thrown.expectCause(isA(IllegalArgumentException.class));
    thrown.expectMessage("must be no earlier");
    thrown.expectMessage(
        String.format("timestamp of the current input (%s)", new Instant(0).toString()));
    thrown.expectMessage(
        String.format(
            "the allowed skew (%s)", PeriodFormat.getDefault().print(Duration.ZERO.toPeriod())));
    // An element output before (current time - skew) is forbidden
    runner.processElement(
        WindowedValue.timestampedValueInGlobalWindow(Duration.millis(1L), new Instant(0)));
  }

}