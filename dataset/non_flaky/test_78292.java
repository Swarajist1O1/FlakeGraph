class DummyClass_78292 {
  @Test
  public void testIsReady() {
    SideInputHandler sideInputHandler =
        new SideInputHandler(
            ImmutableList.of(view1, view2), InMemoryStateInternals.<Void>forKey(null));

    IntervalWindow firstWindow = new IntervalWindow(new Instant(0), new Instant(WINDOW_MSECS_1));

    IntervalWindow secondWindow = new IntervalWindow(new Instant(0), new Instant(WINDOW_MSECS_2));

    // side input should not yet be ready
    assertFalse(sideInputHandler.isReady(view1, firstWindow));

    // add a value for view1
    sideInputHandler.addSideInputValue(
        view1,
        valuesInWindow(
            materializeValuesFor(View.asIterable(), "Hello"), new Instant(0), firstWindow));

    // now side input should be ready
    assertTrue(sideInputHandler.isReady(view1, firstWindow));

    // second window input should still not be ready
    assertFalse(sideInputHandler.isReady(view1, secondWindow));
  }

}