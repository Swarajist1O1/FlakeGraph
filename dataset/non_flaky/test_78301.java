class DummyClass_78301 {
  @Test
  public void testInvokeProcessElementVoluntaryReturnStop() throws Exception {
    SplittableProcessElementInvoker<Void, String, OffsetRange, OffsetRangeTracker>.Result res =
        runTest(5, Duration.ZERO, Integer.MAX_VALUE, Duration.millis(100));
    assertFalse(res.getContinuation().shouldResume());
    assertNull(res.getResidualRestriction());
  }

}