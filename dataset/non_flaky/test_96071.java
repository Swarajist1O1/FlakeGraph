class DummyClass_96071 {
  @Test
  public void testQuantifiableEntityNormalizingAnnotator() {
    Annotation document = new Annotation(text);
    pipeline.annotate(document);

    int i = 0;
    for (CoreMap sentence: document.get(CoreAnnotations.SentencesAnnotation.class)) {
        List<CoreLabel> tokens = sentence.get(CoreAnnotations.TokensAnnotation.class);
        for (CoreLabel token : tokens) {
          System.out.println(token.get(CoreAnnotations.TextAnnotation.class) + ": " + token.get(CoreAnnotations.NamedEntityTagAnnotation.class) + ", " + token.get(CoreAnnotations.NormalizedNamedEntityTagAnnotation.class));
        }
      for (CoreLabel token : tokens) {
        String normalization = token.get(CoreAnnotations.NormalizedNamedEntityTagAnnotation.class);
        if (normalization != null) {
          Assert.assertEquals(answer_text[i], token.get(CoreAnnotations.OriginalTextAnnotation.class));
          Assert.assertEquals(answer_time[i], normalization);
          i++;
        }
      }
    }
    Assert.assertEquals(answer_text.length, i);
    Assert.assertEquals(answer_time.length, i);
  }

}