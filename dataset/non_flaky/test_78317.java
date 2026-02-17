class DummyClass_78317 {
  @Test
  public void testOnElementBufferingDiscarding() throws Exception {
    // Test basic execution of a trigger using a non-combining window set and discarding mode.
    MetricsContainerImpl container = new MetricsContainerImpl("any");
    MetricsEnvironment.setCurrentContainer(container);
    ReduceFnTester<Integer, Iterable<Integer>, IntervalWindow> tester =
        ReduceFnTester.nonCombining(
            FixedWindows.of(Duration.millis(10)),
            mockTriggerStateMachine,
            AccumulationMode.DISCARDING_FIRED_PANES,
            Duration.millis(100),
            ClosingBehavior.FIRE_IF_NON_EMPTY);

    // Pane of {1, 2}
    injectElement(tester, 1);
    when(mockTriggerStateMachine.shouldFire(anyTriggerContext())).thenReturn(true);
    injectElement(tester, 2);
    assertThat(
        tester.extractOutput(),
        contains(isSingleWindowedValue(containsInAnyOrder(1, 2), 1, 0, 10)));

    // Pane of just 3, and finish
    when(mockTriggerStateMachine.shouldFire(anyTriggerContext())).thenReturn(true);
    triggerShouldFinish(mockTriggerStateMachine);
    injectElement(tester, 3);
    assertThat(
        tester.extractOutput(), contains(isSingleWindowedValue(containsInAnyOrder(3), 3, 0, 10)));
    assertTrue(tester.isMarkedFinished(firstWindow));
    tester.assertHasOnlyGlobalAndFinishedSetsFor(firstWindow);

    // This element shouldn't be seen, because the trigger has finished
    injectElement(tester, 4);

    long droppedElements =
        container
            .getCounter(
                MetricName.named(ReduceFnRunner.class, ReduceFnRunner.DROPPED_DUE_TO_CLOSED_WINDOW))
            .getCumulative();
    assertEquals(1, droppedElements);
  }

}