class DummyClass_78325 {
  @Test
  public void testCombiningAccumulatingProcessingTime() throws Exception {
    WindowingStrategy<?, IntervalWindow> strategy =
        WindowingStrategy.of((WindowFn<?, IntervalWindow>) FixedWindows.of(Duration.millis(100)))
            .withTimestampCombiner(TimestampCombiner.EARLIEST)
            .withMode(AccumulationMode.ACCUMULATING_FIRED_PANES)
            .withAllowedLateness(Duration.ZERO)
            .withTrigger(
                Repeatedly.forever(
                    AfterProcessingTime.pastFirstElementInPane().plusDelayOf(Duration.millis(10))));

    ReduceFnTester<Integer, Integer, IntervalWindow> tester =
        ReduceFnTester.combining(strategy, Sum.ofIntegers(), VarIntCoder.of());

    tester.advanceProcessingTime(new Instant(5000));
    injectElement(tester, 2); // processing timer @ 5000 + 10; EOW timer @ 100
    injectElement(tester, 5);

    tester.advanceInputWatermarkNoTimers(new Instant(100));
    tester.advanceProcessingTimeNoTimers(new Instant(5010));

    // Fires the GC/EOW timer at the same time as the processing time timer.
    tester.fireTimers(
        new IntervalWindow(new Instant(0), new Instant(100)),
        TimestampedValue.of(TimeDomain.EVENT_TIME, new Instant(100)),
        TimestampedValue.of(TimeDomain.PROCESSING_TIME, new Instant(5010)));

    assertThat(
        tester.extractOutput(),
        contains(
            isSingleWindowedValue(
                equalTo(7), 2, 0, 100, PaneInfo.createPane(true, true, Timing.ON_TIME, 0, 0))));
  }

}