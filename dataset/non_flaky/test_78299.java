class DummyClass_78299 {
  @Test
  public void testInvokeProcessElementTimeBounded() throws Exception {
    SplittableProcessElementInvoker<Void, String, OffsetRange, OffsetRangeTracker>.Result res =
        runTest(10000, Duration.ZERO, Integer.MAX_VALUE, Duration.millis(100));
    assertFalse(res.getContinuation().shouldResume());
    OffsetRange residualRange = res.getResidualRestriction();
    // Should process ideally around 30 elements - but due to timing flakiness, we can't enforce
    // that precisely. Just test that it's not egregiously off.
    assertThat(residualRange.getFrom(), greaterThan(10L));
    assertThat(residualRange.getFrom(), lessThan(100L));
    assertEquals(10000, residualRange.getTo());
  }

}