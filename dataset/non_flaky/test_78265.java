class DummyClass_78265 {
  @Test
  public void testCheckpointsAfterDuration() throws Exception {
    // Don't bound number of outputs.
    int max = Integer.MAX_VALUE;
    // But bound bundle duration - the bundle should terminate.
    Duration maxBundleDuration = Duration.standardSeconds(1);
    // Create an fn that attempts to 2x output more than checkpointing allows.
    DoFn<Integer, String> fn = new CounterFn(Integer.MAX_VALUE);
    Instant base = Instant.now();
    int baseIndex = 42;

    ProcessFnTester<Integer, String, OffsetRange, Long, OffsetRangeTracker> tester =
        new ProcessFnTester<>(
            base,
            fn,
            BigEndianIntegerCoder.of(),
            SerializableCoder.of(OffsetRange.class),
            max,
            maxBundleDuration);

    List<String> elements;

    tester.startElement(baseIndex, new OffsetRange(0, Long.MAX_VALUE));
    // Bundle should terminate, and should do at least some processing.
    elements = tester.takeOutputElements();
    assertFalse(elements.isEmpty());
    // Bundle should have run for at least the requested duration.
    assertThat(
        Instant.now().getMillis() - base.getMillis(),
        greaterThanOrEqualTo(maxBundleDuration.getMillis()));
  }

}