class DummyClass_78294 {
  @Test
  public void testMultipleWindows() {
    SideInputHandler sideInputHandler =
        new SideInputHandler(ImmutableList.of(view1), InMemoryStateInternals.<Void>forKey(null));

    // two windows that we'll later use for adding elements/retrieving side input
    IntervalWindow firstWindow = new IntervalWindow(new Instant(0), new Instant(WINDOW_MSECS_1));
    IntervalWindow secondWindow =
        new IntervalWindow(new Instant(1000), new Instant(1000 + WINDOW_MSECS_2));

    // add a first value for view1 in the first window
    sideInputHandler.addSideInputValue(
        view1,
        valuesInWindow(
            materializeValuesFor(View.asIterable(), "Hello"), new Instant(0), firstWindow));

    assertThat(sideInputHandler.get(view1, firstWindow), contains("Hello"));

    // add something for second window of view1
    sideInputHandler.addSideInputValue(
        view1,
        valuesInWindow(
            materializeValuesFor(View.asIterable(), "Arrivederci"), new Instant(0), secondWindow));

    assertThat(sideInputHandler.get(view1, secondWindow), contains("Arrivederci"));

    // contents for first window should be unaffected
    assertThat(sideInputHandler.get(view1, firstWindow), contains("Hello"));
  }

}