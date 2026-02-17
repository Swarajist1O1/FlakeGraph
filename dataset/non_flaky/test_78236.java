class DummyClass_78236 {
  @Test
  public void processElementSideInputReadyAllWindows() {
    when(reader.isReady(Mockito.eq(singletonView), Mockito.any(BoundedWindow.class)))
        .thenReturn(true);

    ImmutableList<PCollectionView<?>> views = ImmutableList.of(singletonView);
    SimplePushbackSideInputDoFnRunner<Integer, Integer> runner = createRunner(views);

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
    assertThat(
        underlying.inputElems,
        containsInAnyOrder(ImmutableList.copyOf(multiWindow.explodeWindows()).toArray()));
  }

}