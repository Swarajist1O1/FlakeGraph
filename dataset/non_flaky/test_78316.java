class DummyClass_78316 {
  @Test
  public void testProcessingTimeTimerDoesNotGc() throws Exception {
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

    tester.advanceProcessingTime(new Instant(10000));

    tester.assertHasOnlyGlobalAndStateFor(new IntervalWindow(new Instant(0), new Instant(100)));

    assertThat(
        tester.extractOutput(),
        contains(
            isSingleWindowedValue(
                equalTo(7), 2, 0, 100, PaneInfo.createPane(true, false, Timing.EARLY, 0, 0))));
  }

}