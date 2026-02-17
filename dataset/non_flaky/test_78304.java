class DummyClass_78304 {
  @Test
  public void testInvokeProcessElementOutputDisallowedAfterFailedTryClaim() throws Exception {
    DoFn<Void, String> brokenFn =
        new DoFn<Void, String>() {
          @ProcessElement
          public void process(ProcessContext c, OffsetRangeTracker tracker) {
            assertFalse(tracker.tryClaim(6L));
            c.output("foo");
          }

}