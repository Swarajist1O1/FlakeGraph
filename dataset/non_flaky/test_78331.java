class DummyClass_78331 {
  @Test
  public void testWatermarkHoldAndLateData() throws Exception {
    MetricsContainerImpl container = new MetricsContainerImpl("any");
    MetricsEnvironment.setCurrentContainer(container);
    // Test handling of late data. Specifically, ensure the watermark hold is correct.
    Duration allowedLateness = Duration.millis(10);
    ReduceFnTester<Integer, Iterable<Integer>, IntervalWindow> tester =
        ReduceFnTester.nonCombining(
            FixedWindows.of(Duration.millis(10)),
            mockTriggerStateMachine,
            AccumulationMode.ACCUMULATING_FIRED_PANES,
            allowedLateness,
            ClosingBehavior.FIRE_IF_NON_EMPTY);

    // Input watermark -> null
    assertEquals(null, tester.getWatermarkHold());
    assertEquals(null, tester.getOutputWatermark());

    // All on time data, verify watermark hold.
    IntervalWindow expectedWindow = new IntervalWindow(new Instant(0), new Instant(10));
    injectElement(tester, 1);
    injectElement(tester, 3);
    assertEquals(new Instant(1), tester.getWatermarkHold());
    when(mockTriggerStateMachine.shouldFire(anyTriggerContext())).thenReturn(true);
    injectElement(tester, 2);
    List<WindowedValue<Iterable<Integer>>> output = tester.extractOutput();
    assertThat(
        output,
        contains(
            isSingleWindowedValue(
                containsInAnyOrder(1, 2, 3),
                equalTo(new Instant(1)),
                equalTo((BoundedWindow) expectedWindow))));
    assertThat(
        output.get(0).getPane(), equalTo(PaneInfo.createPane(true, false, Timing.EARLY, 0, -1)));

    // There is no end-of-window hold, but the timer set by the trigger holds the watermark
    assertThat(tester.getWatermarkHold(), nullValue());

    // Nothing dropped.
    long droppedElements =
        container
            .getCounter(
                MetricName.named(ReduceFnRunner.class, ReduceFnRunner.DROPPED_DUE_TO_CLOSED_WINDOW))
            .getCumulative();
    assertEquals(0, droppedElements);

    // Input watermark -> 4, output watermark should advance that far as well
    tester.advanceInputWatermark(new Instant(4));
    assertEquals(new Instant(4), tester.getOutputWatermark());

    // Some late, some on time. Verify that we only hold to the minimum of on-time.
    when(mockTriggerStateMachine.shouldFire(anyTriggerContext())).thenReturn(false);
    tester.advanceInputWatermark(new Instant(4));
    injectElement(tester, 2);
    injectElement(tester, 3);

    // Late data has arrived behind the _output_ watermark. The ReduceFnRunner sets a GC hold
    // since this data is not permitted to hold up the output watermark.
    assertThat(
        tester.getWatermarkHold(), equalTo(expectedWindow.maxTimestamp().plus(allowedLateness)));

    // Now data just ahead of the output watermark arrives and sets an earlier "element" hold
    injectElement(tester, 5);
    assertEquals(new Instant(5), tester.getWatermarkHold());

    when(mockTriggerStateMachine.shouldFire(anyTriggerContext())).thenReturn(true);
    injectElement(tester, 4);
    output = tester.extractOutput();
    assertThat(
        output,
        contains(
            isSingleWindowedValue(
                containsInAnyOrder(
                    1, 2, 3, // earlier firing
                    2, 3, 4, 5), // new elements
                4, // timestamp
                0, // window start
                10))); // window end
    assertThat(
        output.get(0).getPane(), equalTo(PaneInfo.createPane(false, false, Timing.EARLY, 1, -1)));

    // Since the element hold is cleared, there is no hold remaining
    assertThat(tester.getWatermarkHold(), nullValue());

    // All behind the output watermark -- hold is at GC time (if we imagine the
    // trigger sets a timer for ON_TIME firing, that is actually when they'll be emitted)
    when(mockTriggerStateMachine.shouldFire(anyTriggerContext())).thenReturn(false);
    tester.advanceInputWatermark(new Instant(8));
    injectElement(tester, 6);
    injectElement(tester, 5);
    assertThat(
        tester.getWatermarkHold(), equalTo(expectedWindow.maxTimestamp().plus(allowedLateness)));

    injectElement(tester, 4);

    // Fire the ON_TIME pane
    when(mockTriggerStateMachine.shouldFire(anyTriggerContext())).thenReturn(true);

    // To get an ON_TIME pane, we need the output watermark to be held back a little; this would
    // be done by way of the timers set by the trigger, which are mocked here
    tester.setAutoAdvanceOutputWatermark(false);

    tester.advanceInputWatermark(expectedWindow.maxTimestamp().plus(1));
    tester.fireTimer(expectedWindow, expectedWindow.maxTimestamp(), TimeDomain.EVENT_TIME);

    // Output time is end of the window, because all the new data was late, but the pane
    // is the ON_TIME pane.
    output = tester.extractOutput();
    assertThat(
        output,
        contains(
            isSingleWindowedValue(
                containsInAnyOrder(
                    1, 2, 3, // earlier firing
                    2, 3, 4, 5, // earlier firing
                    4, 5, 6), // new elements
                9, // timestamp
                0, // window start
                10))); // window end
    assertThat(
        output.get(0).getPane(), equalTo(PaneInfo.createPane(false, false, Timing.ON_TIME, 2, 0)));

    tester.setAutoAdvanceOutputWatermark(true);

    // This is "pending" at the time the watermark makes it way-late.
    // Because we're about to expire the window, we output it.
    when(mockTriggerStateMachine.shouldFire(anyTriggerContext())).thenReturn(false);
    injectElement(tester, 8);
    droppedElements =
        container
            .getCounter(
                MetricName.named(ReduceFnRunner.class, ReduceFnRunner.DROPPED_DUE_TO_CLOSED_WINDOW))
            .getCumulative();
    assertEquals(0, droppedElements);

    // Exceed the GC limit, triggering the last pane to be fired
    tester.advanceInputWatermark(new Instant(50));
    output = tester.extractOutput();
    // Output time is still end of the window, because the new data (8) was behind
    // the output watermark.
    assertThat(
        output,
        contains(
            isSingleWindowedValue(
                containsInAnyOrder(
                    1, 2, 3, // earlier firing
                    2, 3, 4, 5, // earlier firing
                    4, 5, 6, // earlier firing
                    8), // new element prior to window becoming expired
                9, // timestamp
                0, // window start
                10))); // window end
    assertThat(
        output.get(0).getPane(), equalTo(PaneInfo.createPane(false, true, Timing.LATE, 3, 1)));
    assertEquals(new Instant(50), tester.getOutputWatermark());
    assertEquals(null, tester.getWatermarkHold());

    // Late timers are ignored
    tester.fireTimer(
        new IntervalWindow(new Instant(0), new Instant(10)),
        new Instant(12),
        TimeDomain.EVENT_TIME);

    // And because we're past the end of window + allowed lateness, everything should be cleaned up.
    assertFalse(tester.isMarkedFinished(firstWindow));
    tester.assertHasOnlyGlobalAndFinishedSetsFor();
  }

}