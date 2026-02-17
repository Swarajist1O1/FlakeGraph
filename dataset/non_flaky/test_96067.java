class DummyClass_96067 {
  @Test
  public void testBasicMatching() {
    String str = "President Barack Obama lives in Chicago , Illinois , " +
    "and is a practicing Christian .";
    Annotation document = createDocument(str);
    annotator.annotate(document);
    List<CoreLabel> tokens = document.get(CoreAnnotations.TokensAnnotation.class);

    checkNerTags(tokens,
      "TITLE", "PERSON", "PERSON", "O", "O", "LOCATION", "O", "STATE_OR_PROVINCE",
      "O", "O", "O", "O", "O", "IDEOLOGY", "O");

  }

}