class DummyClass_78329 {
  @Test
  public void testOnElementCombiningAccumulating() throws Exception {
    // Test basic execution of a trigger using a non-combining window set and accumulating mode.
    WindowingStrategy<?, IntervalWindow> strategy =
        WindowingStrategy.of((WindowFn<?, IntervalWindow>) FixedWindows.of(Duration.millis(10)))
            .withTimestampCombiner(TimestampCombiner.EARLIEST)
            .withMode(AccumulationMode.ACCUMULATING_FIRED_PANES)
            .withAllowedLateness(Duration.millis(100));

    ReduceFnTester<Integer, Integer, IntervalWindow> tester =
        ReduceFnTester.combining(
            strategy, mockTriggerStateMachine, Sum.ofIntegers(), VarIntCoder.of());

    injectElement(tester, 1);

    when(mockTriggerStateMachine.shouldFire(anyTriggerContext())).thenReturn(true);
    injectElement(tester, 2);

    when(mockTriggerStateMachine.shouldFire(anyTriggerContext())).thenReturn(true);
    triggerShouldFinish(mockTriggerStateMachine);
    injectElement(tester, 3);

    // This element shouldn't be seen, because the trigger has finished
    injectElement(tester, 4);

    assertThat(
        tester.extractOutput(),
        contains(
            isSingleWindowedValue(equalTo(3), 1, 0, 10),
            isSingleWindowedValue(equalTo(6), 3, 0, 10)));
    assertTrue(tester.isMarkedFinished(firstWindow));
    tester.assertHasOnlyGlobalAndFinishedSetsFor(firstWindow);
  }

}