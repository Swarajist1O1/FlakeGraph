class DummyClass_78262 {
  @Test
  public void testResumeSetsTimer() throws Exception {
    DoFn<Integer, String> fn = new SelfInitiatedResumeFn();
    Instant base = Instant.now();
    ProcessFnTester<Integer, String, SomeRestriction, Void, SomeRestrictionTracker> tester =
        new ProcessFnTester<>(
            base,
            fn,
            BigEndianIntegerCoder.of(),
            SerializableCoder.of(SomeRestriction.class),
            MAX_OUTPUTS_PER_BUNDLE,
            MAX_BUNDLE_DURATION);

    tester.startElement(42, new SomeRestriction());
    assertThat(tester.takeOutputElements(), contains("42"));

    // Should resume after 5 seconds: advancing by 3 seconds should have no effect.
    assertFalse(tester.advanceProcessingTimeBy(Duration.standardSeconds(3)));
    assertTrue(tester.takeOutputElements().isEmpty());

    // 6 seconds should be enough  should invoke the fn again.
    assertTrue(tester.advanceProcessingTimeBy(Duration.standardSeconds(3)));
    assertThat(tester.takeOutputElements(), contains("42"));

    // Should again resume after 5 seconds: advancing by 3 seconds should again have no effect.
    assertFalse(tester.advanceProcessingTimeBy(Duration.standardSeconds(3)));
    assertTrue(tester.takeOutputElements().isEmpty());

    // 6 seconds should again be enough.
    assertTrue(tester.advanceProcessingTimeBy(Duration.standardSeconds(3)));
    assertThat(tester.takeOutputElements(), contains("42"));
  }

}