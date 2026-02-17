class DummyClass_78296 {
  @Test
  public void testLateDataFilter() throws Exception {
    MetricsContainerImpl container = new MetricsContainerImpl("any");
    MetricsEnvironment.setCurrentContainer(container);
    when(mockTimerInternals.currentInputWatermarkTime()).thenReturn(new Instant(15L));

    LateDataFilter lateDataFilter =
        new LateDataFilter(WindowingStrategy.of(WINDOW_FN), mockTimerInternals);

    Iterable<WindowedValue<Integer>> actual =
        lateDataFilter.filter(
            "a",
            ImmutableList.of(
                createDatum(13, 13L),
                createDatum(5, 5L), // late element, earlier than 4L.
                createDatum(16, 16L),
                createDatum(18, 18L)));

    Iterable<WindowedValue<Integer>> expected =
        ImmutableList.of(createDatum(13, 13L), createDatum(16, 16L), createDatum(18, 18L));
    assertThat(expected, containsInAnyOrder(Iterables.toArray(actual, WindowedValue.class)));
    long droppedValues =
        container
            .getCounter(
                MetricName.named(
                    LateDataDroppingDoFnRunner.class,
                    LateDataDroppingDoFnRunner.DROPPED_DUE_TO_LATENESS))
            .getCumulative();
    assertEquals(1, droppedValues);
    // Ensure that reiterating returns the same results and doesn't increment the counter again.
    assertThat(expected, containsInAnyOrder(Iterables.toArray(actual, WindowedValue.class)));
    droppedValues =
        container
            .getCounter(
                MetricName.named(
                    LateDataDroppingDoFnRunner.class,
                    LateDataDroppingDoFnRunner.DROPPED_DUE_TO_LATENESS))
            .getCumulative();
    assertEquals(1, droppedValues);
  }

}