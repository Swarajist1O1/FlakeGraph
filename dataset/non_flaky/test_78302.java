class DummyClass_78302 {
  @Test
  public void testInvokeProcessElementVoluntaryReturnResume() throws Exception {
    SplittableProcessElementInvoker<Void, String, OffsetRange, OffsetRangeTracker>.Result res =
        runTest(10, Duration.ZERO, 5, Duration.millis(100));
    assertTrue(res.getContinuation().shouldResume());
    assertEquals(new OffsetRange(5, 10), res.getResidualRestriction());
  }

}