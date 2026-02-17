class DummyClass_78322 {
  @Test
  public void testOnlyOneOnTimePane() throws Exception {
    WindowingStrategy<?, IntervalWindow> strategy =
        WindowingStrategy.of((WindowFn<?, IntervalWindow>) FixedWindows.of(Duration.millis(10)))
            .withTrigger(DefaultTrigger.of())
            .withMode(AccumulationMode.ACCUMULATING_FIRED_PANES)
            .withAllowedLateness(Duration.millis(100));

    ReduceFnTester<Integer, Integer, IntervalWindow> tester =
        ReduceFnTester.combining(strategy, Sum.ofIntegers(), VarIntCoder.of());

    tester.advanceInputWatermark(new Instant(0));

    int value1 = 1;
    int value2 = 3;

    // A single element that should be in the ON_TIME output
    tester.injectElements(TimestampedValue.of(value1, new Instant(1)));

    // Should fire ON_TIME
    tester.advanceInputWatermark(new Instant(10));

    // The DefaultTrigger should cause output labeled LATE, even though it does not have to be
    // labeled as such.
    tester.injectElements(TimestampedValue.of(value2, new Instant(3)));

    List<WindowedValue<Integer>> output = tester.extractOutput();
    assertEquals(2, output.size());

    assertThat(output.get(0), isWindowedValue(equalTo(value1)));
    assertThat(output.get(1), isWindowedValue(equalTo(value1 + value2)));

    assertThat(
        output.get(0),
        WindowMatchers.valueWithPaneInfo(PaneInfo.createPane(true, false, Timing.ON_TIME, 0, 0)));
    assertThat(
        output.get(1),
        WindowMatchers.valueWithPaneInfo(PaneInfo.createPane(false, false, Timing.LATE, 1, 1)));
  }

}