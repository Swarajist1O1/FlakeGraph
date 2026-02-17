class DummyClass_78300 {
  @Test
  public void testInvokeProcessElementTimeBoundedWithStartupDelay() throws Exception {
    SplittableProcessElementInvoker<Void, String, OffsetRange, OffsetRangeTracker>.Result res =
        runTest(10000, Duration.standardSeconds(3), Integer.MAX_VALUE, Duration.millis(100));
    assertFalse(res.getContinuation().shouldResume());
    OffsetRange residualRange = res.getResidualRestriction();
    // Same as above, but this time it counts from the time of the first tryClaim() call
    assertThat(residualRange.getFrom(), greaterThan(10L));
    assertThat(residualRange.getFrom(), lessThan(100L));
    assertEquals(10000, residualRange.getTo());
  }

}