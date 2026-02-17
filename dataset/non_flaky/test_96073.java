class DummyClass_96073 {
  @Test
  public void testDepparsePipeline() {
    testAnnotatorSequence(Arrays.asList("tokenize", "ssplit", "pos", "depparse"));
  }

}