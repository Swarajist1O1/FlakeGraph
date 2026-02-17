class DummyClass_78267 {
  @Test
  public void beforeEndOfGlobalWindowSame() {
    FixedWindows windowFn = FixedWindows.of(Duration.standardMinutes(5));
    Duration allowedLateness = Duration.standardMinutes(2);
    WindowingStrategy<?, ?> strategy =
        WindowingStrategy.globalDefault()
            .withWindowFn(windowFn)
            .withAllowedLateness(allowedLateness);

    IntervalWindow window = windowFn.assignWindow(new Instant(10));
    assertThat(
        LateDataUtils.garbageCollectionTime(window, strategy),
        equalTo(window.maxTimestamp().plus(allowedLateness)));
  }

}