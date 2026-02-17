class DummyClass_78237 {
  @Test
  public void processElementNoSideInputs() {
    SimplePushbackSideInputDoFnRunner<Integer, Integer> runner = createRunner(ImmutableList.of());

    WindowedValue<Integer> multiWindow =
        WindowedValue.of(
            2,
            new Instant(-2),
            ImmutableList.of(
                new IntervalWindow(new Instant(-500L), new Instant(0L)),
                new IntervalWindow(BoundedWindow.TIMESTAMP_MIN_VALUE, new Instant(250L)),
                GlobalWindow.INSTANCE),
            PaneInfo.ON_TIME_AND_ONLY_FIRING);
    Iterable<WindowedValue<Integer>> multiWindowPushback =
        runner.processElementInReadyWindows(multiWindow);
    assertThat(multiWindowPushback, emptyIterable());
    // Should preserve the compressed representation when there's no side inputs.
    assertThat(underlying.inputElems, containsInAnyOrder(multiWindow));
  }

}