class DummyClass_78323 {
  @Test
  public void testOnElementCombiningDiscarding() throws Exception {
    // Test basic execution of a trigger using a non-combining window set and discarding mode.
    WindowingStrategy<?, IntervalWindow> strategy =
        WindowingStrategy.of((WindowFn<?, IntervalWindow>) FixedWindows.of(Duration.millis(10)))
            .withTimestampCombiner(TimestampCombiner.EARLIEST)
            .withMode(AccumulationMode.DISCARDING_FIRED_PANES)
            .withAllowedLateness(Duration.millis(100));

    ReduceFnTester<Integer, Integer, IntervalWindow> tester =
        ReduceFnTester.combining(
            strategy, mockTriggerStateMachine, Sum.ofIntegers(), VarIntCoder.of());

    injectElement(tester, 2);

    when(mockTriggerStateMachine.shouldFire(anyTriggerContext())).thenReturn(true);
    injectElement(tester, 3);

    when(mockTriggerStateMachine.shouldFire(anyTriggerContext())).thenReturn(true);
    triggerShouldFinish(mockTriggerStateMachine);
    injectElement(tester, 4);

    // This element shouldn't be seen, because the trigger has finished
    injectElement(tester, 6);

    assertThat(
        tester.extractOutput(),
        contains(
            isSingleWindowedValue(equalTo(5), 2, 0, 10),
            isSingleWindowedValue(equalTo(4), 4, 0, 10)));
    assertTrue(tester.isMarkedFinished(firstWindow));
    tester.assertHasOnlyGlobalAndFinishedSetsFor(firstWindow);
  }

}