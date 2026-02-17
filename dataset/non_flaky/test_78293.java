class DummyClass_78293 {
  @Test
  public void testNewInputReplacesPreviousInput() {
    // new input should completely replace old input
    // the creation of the Iterable that has the side input
    // contents happens upstream. this is also where
    // accumulation/discarding is decided.

    SideInputHandler sideInputHandler =
        new SideInputHandler(ImmutableList.of(view1), InMemoryStateInternals.<Void>forKey(null));

    IntervalWindow window = new IntervalWindow(new Instant(0), new Instant(WINDOW_MSECS_1));

    // add a first value for view1
    sideInputHandler.addSideInputValue(
        view1,
        valuesInWindow(materializeValuesFor(View.asIterable(), "Hello"), new Instant(0), window));

    assertThat(sideInputHandler.get(view1, window), contains("Hello"));

    // subsequent values should replace existing values
    sideInputHandler.addSideInputValue(
        view1,
        valuesInWindow(
            materializeValuesFor(View.asIterable(), "Ciao", "Buongiorno"), new Instant(0), window));

    assertThat(sideInputHandler.get(view1, window), contains("Ciao", "Buongiorno"));
  }

}