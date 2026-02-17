class DummyClass_78235 {
  @Test
  public void processElementSideInputNotReadySomeWindows() {
    when(reader.isReady(Mockito.eq(singletonView), Mockito.eq(GlobalWindow.INSTANCE)))
        .thenReturn(false);
    when(reader.isReady(
            Mockito.eq(singletonView),
            org.mockito.AdditionalMatchers.not(Mockito.eq(GlobalWindow.INSTANCE))))
        .thenReturn(true);

    SimplePushbackSideInputDoFnRunner<Integer, Integer> runner =
        createRunner(ImmutableList.of(singletonView));

    IntervalWindow littleWindow = new IntervalWindow(new Instant(-500L), new Instant(0L));
    IntervalWindow bigWindow =
        new IntervalWindow(BoundedWindow.TIMESTAMP_MIN_VALUE, new Instant(250L));
    WindowedValue<Integer> multiWindow =
        WindowedValue.of(
            2,
            new Instant(-2),
            ImmutableList.of(littleWindow, bigWindow, GlobalWindow.INSTANCE),
            PaneInfo.NO_FIRING);
    Iterable<WindowedValue<Integer>> multiWindowPushback =
        runner.processElementInReadyWindows(multiWindow);
    assertThat(
        multiWindowPushback,
        containsInAnyOrder(WindowedValue.timestampedValueInGlobalWindow(2, new Instant(-2L))));
    assertThat(
        underlying.inputElems,
        containsInAnyOrder(
            WindowedValue.of(
                2, new Instant(-2), ImmutableList.of(littleWindow), PaneInfo.NO_FIRING),
            WindowedValue.of(2, new Instant(-2), ImmutableList.of(bigWindow), PaneInfo.NO_FIRING)));
  }

}