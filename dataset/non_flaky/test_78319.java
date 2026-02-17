class DummyClass_78319 {
  @Test
  public void testSessionEowAndGcTogether() throws Exception {
    ReduceFnTester<Integer, Iterable<Integer>, IntervalWindow> tester =
        ReduceFnTester.nonCombining(
            Sessions.withGapDuration(Duration.millis(10)),
            DefaultTriggerStateMachine.of(),
            AccumulationMode.ACCUMULATING_FIRED_PANES,
            Duration.millis(50),
            ClosingBehavior.FIRE_ALWAYS);

    tester.setAutoAdvanceOutputWatermark(true);

    tester.advanceInputWatermark(new Instant(0));
    injectElement(tester, 1);
    tester.advanceInputWatermark(new Instant(100));

    assertThat(
        tester.extractOutput(),
        contains(
            isSingleWindowedValue(
                contains(1), 1, 1, 11, PaneInfo.createPane(true, true, Timing.ON_TIME))));
  }

}