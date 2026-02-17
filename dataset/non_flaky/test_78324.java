class DummyClass_78324 {
  @Test
  public void testLateProcessingTimeTimer() throws Exception {
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

    // After this advancement, the window is expired and only the GC process
    // should be allowed to touch it
    tester.advanceInputWatermarkNoTimers(new Instant(100));

    // This should not output
    tester.advanceProcessingTime(new Instant(6000));

    assertThat(tester.extractOutput(), emptyIterable());
  }

}