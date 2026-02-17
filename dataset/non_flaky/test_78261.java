class DummyClass_78261 {
  @Test
  public void testUpdatesWatermark() throws Exception {
    DoFn<Instant, String> fn = new WatermarkUpdateFn();
    Instant base = Instant.now();

    ProcessFnTester<Instant, String, OffsetRange, Long, OffsetRangeTracker> tester =
        new ProcessFnTester<>(
            base,
            fn,
            InstantCoder.of(),
            SerializableCoder.of(OffsetRange.class),
            3,
            MAX_BUNDLE_DURATION);

    tester.startElement(base, new OffsetRange(0, 8));
    assertThat(tester.takeOutputElements(), hasItems("0", "1", "2"));
    assertEquals(base.plus(Duration.standardSeconds(2)), tester.getWatermarkHold());

    assertTrue(tester.advanceProcessingTimeBy(Duration.standardSeconds(1)));
    assertThat(tester.takeOutputElements(), hasItems("3", "4", "5"));
    assertEquals(base.plus(Duration.standardSeconds(5)), tester.getWatermarkHold());

    assertTrue(tester.advanceProcessingTimeBy(Duration.standardSeconds(1)));
    assertThat(tester.takeOutputElements(), hasItems("6", "7"));
    assertEquals(null, tester.getWatermarkHold());
  }

}