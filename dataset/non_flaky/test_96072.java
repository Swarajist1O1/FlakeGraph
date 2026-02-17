class DummyClass_96072 {
  @Test
  public void testDefaultPipeline() {
    testAnnotatorSequence(Arrays.asList("tokenize", "ssplit", "pos", "lemma", "ner", "gender", "parse", "coref"));
  }

}