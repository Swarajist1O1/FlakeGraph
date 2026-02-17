class DummyClass_78318 {
  @Test
  public void testOnElementBufferingAccumulating() throws Exception {
    // Test basic execution of a trigger using a non-combining window set and accumulating mode.
    ReduceFnTester<Integer, Iterable<Integer>, IntervalWindow> tester =
        ReduceFnTester.nonCombining(
            FixedWindows.of(Duration.millis(10)),
            mockTriggerStateMachine,
            AccumulationMode.ACCUMULATING_FIRED_PANES,
            Duration.millis(100),
            ClosingBehavior.FIRE_IF_NON_EMPTY);

    injectElement(tester, 1);

    // Fires {1, 2}
    when(mockTriggerStateMachine.shouldFire(anyTriggerContext())).thenReturn(true);
    injectElement(tester, 2);

    // Fires {1, 2, 3} because we are in accumulating mode
    when(mockTriggerStateMachine.shouldFire(anyTriggerContext())).thenReturn(true);
    triggerShouldFinish(mockTriggerStateMachine);
    injectElement(tester, 3);

    // This element shouldn't be seen, because the trigger has finished
    injectElement(tester, 4);

    assertThat(
        tester.extractOutput(),
        contains(
            isSingleWindowedValue(containsInAnyOrder(1, 2), 1, 0, 10),
            isSingleWindowedValue(containsInAnyOrder(1, 2, 3), 3, 0, 10)));
    assertTrue(tester.isMarkedFinished(firstWindow));
    tester.assertHasOnlyGlobalAndFinishedSetsFor(firstWindow);
  }

}