class DummyClass_96058 {
  @Test
  public void testMorphaAnnotator() {
    Annotation document = new Annotation(text);
    fullPipeline.annotate(document);
    checkResult(document.get(CoreAnnotations.TokensAnnotation.class));
  }

}