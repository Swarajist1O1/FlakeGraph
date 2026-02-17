class DummyClass_78268 {
  @Test
  public void garbageCollectionTimeAfterEndOfGlobalWindow() {
    FixedWindows windowFn = FixedWindows.of(Duration.standardMinutes(5));
    WindowingStrategy<?, ?> strategy = WindowingStrategy.globalDefault().withWindowFn(windowFn);

    IntervalWindow window = windowFn.assignWindow(new Instant(BoundedWindow.TIMESTAMP_MAX_VALUE));
    assertThat(window.maxTimestamp(), equalTo(GlobalWindow.INSTANCE.maxTimestamp()));
    assertThat(
        LateDataUtils.garbageCollectionTime(window, strategy),
        equalTo(GlobalWindow.INSTANCE.maxTimestamp()));
  }

}