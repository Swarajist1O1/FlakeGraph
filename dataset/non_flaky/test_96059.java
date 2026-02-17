class DummyClass_96059 {
  @Test
  public void testSentencesAnnotation() {
    List<CoreLabel> words = getTestWords();

    CoreMap sentence = new ArrayCoreMap();
    sentence.set(CoreAnnotations.TokensAnnotation.class, words);
    List<CoreMap> sentences = new ArrayList<>();
    sentences.add(sentence);
    Annotation document = new Annotation(text);
    document.set(CoreAnnotations.SentencesAnnotation.class, sentences);

    shortPipeline.annotate(document);
    checkResult(words);
  }

}