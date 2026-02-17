class DummyClass_78326 {
  @Test
  public void testFixedWindowEndOfTimeGarbageCollection() throws Exception {
    Duration allowedLateness = Duration.standardDays(365);
    Duration windowSize = Duration.millis(10);
    WindowFn<Object, IntervalWindow> windowFn = FixedWindows.of(windowSize);

    // This timestamp falls into a window where the end of the window is before the end of the
    // global window - the "end of time" - yet its expiration time is after.
    final Instant elementTimestamp =
        GlobalWindow.INSTANCE.maxTimestamp().minus(allowedLateness).plus(1);

    IntervalWindow window =
        Iterables.getOnlyElement(
            windowFn.assignWindows(
                windowFn.new AssignContext() {
                  @Override
                  public Object element() {
                    throw new UnsupportedOperationException();
                  }

}