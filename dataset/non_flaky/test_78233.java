class DummyClass_78233 {
  @Test
  public void processElementSideInputNotReady() {
    when(reader.isReady(Mockito.eq(singletonView), Mockito.any(BoundedWindow.class)))
        .thenReturn(false);

    SimplePushbackSideInputDoFnRunner<Integer, Integer> runner =
        createRunner(ImmutableList.of(singletonView));

    WindowedValue<Integer> oneWindow =
        WindowedValue.of(
            2,
            new Instant(-2),
            new IntervalWindow(new Instant(-500L), new Instant(0L)),
            PaneInfo.ON_TIME_AND_ONLY_FIRING);
    Iterable<WindowedValue<Integer>> oneWindowPushback =
        runner.processElementInReadyWindows(oneWindow);
    assertThat(oneWindowPushback, containsInAnyOrder(oneWindow));
    assertThat(underlying.inputElems, emptyIterable());
  }

}