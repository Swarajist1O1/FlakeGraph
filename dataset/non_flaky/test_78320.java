class DummyClass_78320 {
  @Test
  public void testFixedWindowsEowAndGcTogether() throws Exception {
    ReduceFnTester<Integer, Iterable<Integer>, IntervalWindow> tester =
        ReduceFnTester.nonCombining(
            FixedWindows.of(Duration.millis(10)),
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
                contains(1), 1, 0, 10, PaneInfo.createPane(true, true, Timing.ON_TIME))));
  }

}