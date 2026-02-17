class DummyClass_96074 {
  @Test
  public void testQuotePipeline() {
    testAnnotatorSequence(Arrays.asList("tokenize","ssplit","pos","lemma","ner","depparse","coref","quote"));
  }

}