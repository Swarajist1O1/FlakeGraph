class DummyClass_78330 {
  @Test
  public void testOnElementCombiningWithContext() throws Exception {
    // Create values at timestamps 0 .. 8, windowed into fixed windows of 2.
    // Side input windowed into fixed windows of 4:
    // main: [ 0 1 ] [ 2 3 ] [ 4 5 ] [ 6 7 ]
    // side: [     100     ] [     104     ]
    // Combine using a CombineFn "side input + sum(main inputs)".
    final int firstWindowSideInput = 100;
    final int secondWindowSideInput = 104;
    final Integer expectedValue = firstWindowSideInput;
    WindowingStrategy<?, IntervalWindow> mainInputWindowingStrategy =
        WindowingStrategy.of(FixedWindows.of(Duration.millis(2)))
            .withMode(AccumulationMode.ACCUMULATING_FIRED_PANES);

    WindowMappingFn<?> sideInputWindowMappingFn =
        FixedWindows.of(Duration.millis(4)).getDefaultWindowMappingFn();
    when(mockView.getWindowMappingFn()).thenReturn((WindowMappingFn) sideInputWindowMappingFn);

    TestOptions options = PipelineOptionsFactory.as(TestOptions.class);
    options.setValue(expectedValue);

    when(mockSideInputReader.contains(any(PCollectionView.class))).thenReturn(true);
    when(mockSideInputReader.get(any(PCollectionView.class), any(BoundedWindow.class)))
        .then(
            invocation -> {
              IntervalWindow sideInputWindow = (IntervalWindow) invocation.getArguments()[1];
              long startMs = sideInputWindow.start().getMillis();
              long endMs = sideInputWindow.end().getMillis();
              // Window should have been produced by sideInputWindowingStrategy.
              assertThat(startMs, anyOf(equalTo(0L), equalTo(4L)));
              assertThat(endMs - startMs, equalTo(4L));
              // If startMs == 4 (second window), equal to secondWindowSideInput.
              return firstWindowSideInput + (int) startMs;
            });

    SumAndVerifyContextFn combineFn = new SumAndVerifyContextFn(mockView, expectedValue);
    ReduceFnTester<Integer, Integer, IntervalWindow> tester =
        ReduceFnTester.combining(
            mainInputWindowingStrategy,
            mockTriggerStateMachine,
            combineFn,
            VarIntCoder.of(),
            options,
            mockSideInputReader);

    when(mockTriggerStateMachine.shouldFire(anyTriggerContext())).thenReturn(true);
    for (int i = 0; i < 8; ++i) {
      injectElement(tester, i);
    }

    assertThat(
        tester.extractOutput(),
        contains(
            isSingleWindowedValue(equalTo(0 + firstWindowSideInput), 1, 0, 2),
            isSingleWindowedValue(equalTo(0 + 1 + firstWindowSideInput), 1, 0, 2),
            isSingleWindowedValue(equalTo(2 + firstWindowSideInput), 3, 2, 4),
            isSingleWindowedValue(equalTo(2 + 3 + firstWindowSideInput), 3, 2, 4),
            isSingleWindowedValue(equalTo(4 + secondWindowSideInput), 5, 4, 6),
            isSingleWindowedValue(equalTo(4 + 5 + secondWindowSideInput), 5, 4, 6),
            isSingleWindowedValue(equalTo(6 + secondWindowSideInput), 7, 6, 8),
            isSingleWindowedValue(equalTo(6 + 7 + secondWindowSideInput), 7, 6, 8)));
  }

}