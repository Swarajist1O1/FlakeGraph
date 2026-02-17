class DummyClass_78269 {
  @Test
  public void garbageCollectionTimeAfterEndOfGlobalWindowWithLateness() {
    FixedWindows windowFn = FixedWindows.of(Duration.standardMinutes(5));
    Duration allowedLateness = Duration.millis(Long.MAX_VALUE);
    WindowingStrategy<?, ?> strategy =
        WindowingStrategy.globalDefault()
            .withWindowFn(windowFn)
            .withAllowedLateness(allowedLateness);

    IntervalWindow window = windowFn.assignWindow(new Instant(-100));
    assertThat(
        window.maxTimestamp().plus(allowedLateness),
        Matchers.greaterThan(GlobalWindow.INSTANCE.maxTimestamp()));
    assertThat(
        LateDataUtils.garbageCollectionTime(window, strategy),
        equalTo(GlobalWindow.INSTANCE.maxTimestamp()));
  }

}