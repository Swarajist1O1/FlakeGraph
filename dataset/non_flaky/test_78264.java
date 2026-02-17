class DummyClass_78264 {
  @Test
  public void testCheckpointsAfterNumOutputs() throws Exception {
    int max = 100;
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
            MAX_BUNDLE_DURATION);

    List<String> elements;

    // Create an fn that attempts to 2x output more than checkpointing allows.
    tester.startElement(baseIndex, new OffsetRange(0, 2 * max + max / 2));
    elements = tester.takeOutputElements();
    assertEquals(max, elements.size());
    // Should output the range [0, max)
    assertThat(elements, hasItem(String.valueOf(baseIndex)));
    assertThat(elements, hasItem(String.valueOf(baseIndex + max - 1)));

    assertTrue(tester.advanceProcessingTimeBy(Duration.standardSeconds(1)));
    elements = tester.takeOutputElements();
    assertEquals(max, elements.size());
    // Should output the range [max, 2*max)
    assertThat(elements, hasItem(String.valueOf(baseIndex + max)));
    assertThat(elements, hasItem(String.valueOf(baseIndex + 2 * max - 1)));

    assertTrue(tester.advanceProcessingTimeBy(Duration.standardSeconds(1)));
    elements = tester.takeOutputElements();
    assertEquals(max / 2, elements.size());
    // Should output the range [2*max, 2*max + max/2)
    assertThat(elements, hasItem(String.valueOf(baseIndex + 2 * max)));
    assertThat(elements, hasItem(String.valueOf(baseIndex + 2 * max + max / 2 - 1)));
    assertThat(elements, not(hasItem((String.valueOf(baseIndex + 2 * max + max / 2)))));
  }

}