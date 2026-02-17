class DummyClass_78234 {
  @Test
  public void processElementSideInputNotReadyMultipleWindows() {
    when(reader.isReady(Mockito.eq(singletonView), Mockito.any(BoundedWindow.class)))
        .thenReturn(false);

    SimplePushbackSideInputDoFnRunner<Integer, Integer> runner =
        createRunner(ImmutableList.of(singletonView));

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
    assertThat(multiWindowPushback, equalTo(multiWindow.explodeWindows()));
    assertThat(underlying.inputElems, emptyIterable());
  }

}