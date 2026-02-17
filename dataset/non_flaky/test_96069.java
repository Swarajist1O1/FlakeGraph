class DummyClass_96069 {
  @Test
  public void testPriority() {
    String str = "Christianity is of higher regex priority than Early Christianity . ";
    Annotation document = createDocument(str);
    annotator.annotate(document);
    List<CoreLabel> tokens = document.get(CoreAnnotations.TokensAnnotation.class);
    checkNerTags(tokens, "RELIGION", "O", "O", "O", "O", "O", "O", "O", "RELIGION", "O");
  }

}