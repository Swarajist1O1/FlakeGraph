class DummyClass_78260 {
  @Test
  public void testTrivialProcessFnPropagatesOutputWindowAndTimestamp() throws Exception {
    // Tests that ProcessFn correctly propagates the window and timestamp of the element
    // inside the KeyedWorkItem.
    // The underlying DoFn is actually monolithic, so this doesn't test splitting.
    DoFn<Integer, String> fn = new ToStringFn();

    Instant base = Instant.now();

    IntervalWindow w =
        new IntervalWindow(
            base.minus(Duration.standardMinutes(1)), base.plus(Duration.standardMinutes(1)));

    ProcessFnTester<Integer, String, SomeRestriction, Void, SomeRestrictionTracker> tester =
        new ProcessFnTester<>(
            base,
            fn,
            BigEndianIntegerCoder.of(),
            SerializableCoder.of(SomeRestriction.class),
            MAX_OUTPUTS_PER_BUNDLE,
            MAX_BUNDLE_DURATION);
    tester.startElement(
        WindowedValue.of(
            KV.of(42, new SomeRestriction()),
            base,
            Collections.singletonList(w),
            PaneInfo.ON_TIME_AND_ONLY_FIRING));

    assertEquals(
        Arrays.asList(
            TimestampedValue.of("42a", base),
            TimestampedValue.of("42b", base),
            TimestampedValue.of("42c", base)),
        tester.peekOutputElementsInWindow(w));
  }

}