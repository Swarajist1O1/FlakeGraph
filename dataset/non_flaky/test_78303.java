class DummyClass_78303 {
  @Test
  public void testInvokeProcessElementOutputDisallowedBeforeTryClaim() throws Exception {
    DoFn<Void, String> brokenFn =
        new DoFn<Void, String>() {
          @ProcessElement
          public void process(ProcessContext c, OffsetRangeTracker tracker) {
            c.output("foo");
          }

}