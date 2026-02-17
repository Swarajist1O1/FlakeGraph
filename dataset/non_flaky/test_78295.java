class DummyClass_78295 {
  @Test
  public void testMultipleSideInputs() {
    SideInputHandler sideInputHandler =
        new SideInputHandler(
            ImmutableList.of(view1, view2), InMemoryStateInternals.<Void>forKey(null));

    // two windows that we'll later use for adding elements/retrieving side input
    IntervalWindow firstWindow = new IntervalWindow(new Instant(0), new Instant(WINDOW_MSECS_1));

    // add value for view1 in the first window
    sideInputHandler.addSideInputValue(
        view1,
        valuesInWindow(
            materializeValuesFor(View.asIterable(), "Hello"), new Instant(0), firstWindow));

    assertThat(sideInputHandler.get(view1, firstWindow), contains("Hello"));

    // view2 should not have any data
    assertFalse(sideInputHandler.isReady(view2, firstWindow));

    // also add some data for view2
    sideInputHandler.addSideInputValue(
        view2,
        valuesInWindow(
            materializeValuesFor(View.asIterable(), "Salut"), new Instant(0), firstWindow));

    assertTrue(sideInputHandler.isReady(view2, firstWindow));
    assertThat(sideInputHandler.get(view2, firstWindow), contains("Salut"));

    // view1 should not be affected by that
    assertThat(sideInputHandler.get(view1, firstWindow), contains("Hello"));
  }

}