class DummyClass_78298 {
  @Test
  public void testInvokeProcessElementOutputBounded() throws Exception {
    SplittableProcessElementInvoker<Void, String, OffsetRange, OffsetRangeTracker>.Result res =
        runTest(10000, Duration.ZERO, Integer.MAX_VALUE, Duration.ZERO);
    assertFalse(res.getContinuation().shouldResume());
    OffsetRange residualRange = res.getResidualRestriction();
    // Should process the first 100 elements.
    assertEquals(1000, residualRange.getFrom());
    assertEquals(10000, residualRange.getTo());
  }

}