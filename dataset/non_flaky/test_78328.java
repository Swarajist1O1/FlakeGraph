class DummyClass_78328 {
  @Test
  public void testCombiningAccumulatingEventTime() throws Exception {
    WindowingStrategy<?, IntervalWindow> strategy =
        WindowingStrategy.of((WindowFn<?, IntervalWindow>) FixedWindows.of(Duration.millis(100)))
            .withTimestampCombiner(TimestampCombiner.EARLIEST)
            .withMode(AccumulationMode.ACCUMULATING_FIRED_PANES)
            .withAllowedLateness(Duration.millis(1))
            .withTrigger(Repeatedly.forever(AfterWatermark.pastEndOfWindow()));

    ReduceFnTester<Integer, Integer, IntervalWindow> tester =
        ReduceFnTester.combining(strategy, Sum.ofIntegers(), VarIntCoder.of());

    injectElement(tester, 2); // processing timer @ 5000 + 10; EOW timer @ 100
    injectElement(tester, 5);

    tester.advanceInputWatermark(new Instant(1000));

    assertThat(
        tester.extractOutput(),
        contains(
            isSingleWindowedValue(
                equalTo(7), 2, 0, 100, PaneInfo.createPane(true, true, Timing.ON_TIME, 0, 0))));
  }

}