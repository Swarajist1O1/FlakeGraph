class DummyClass_78263 {
  @Test
  public void testResumeCarriesOverState() throws Exception {
    DoFn<Integer, String> fn = new CounterFn(1);
    Instant base = Instant.now();
    ProcessFnTester<Integer, String, OffsetRange, Long, OffsetRangeTracker> tester =
        new ProcessFnTester<>(
            base,
            fn,
            BigEndianIntegerCoder.of(),
            SerializableCoder.of(OffsetRange.class),
            MAX_OUTPUTS_PER_BUNDLE,
            MAX_BUNDLE_DURATION);

    tester.startElement(42, new OffsetRange(0, 3));
    assertThat(tester.takeOutputElements(), contains("42"));
    assertTrue(tester.advanceProcessingTimeBy(Duration.standardSeconds(1)));
    assertThat(tester.takeOutputElements(), contains("43"));
    assertTrue(tester.advanceProcessingTimeBy(Duration.standardSeconds(1)));
    assertThat(tester.takeOutputElements(), contains("44"));
    assertTrue(tester.advanceProcessingTimeBy(Duration.standardSeconds(1)));
    // After outputting all 3 items, should not output anything more.
    assertEquals(0, tester.takeOutputElements().size());
    // Should also not ask to resume.
    assertFalse(tester.advanceProcessingTimeBy(Duration.standardSeconds(1)));
  }

}