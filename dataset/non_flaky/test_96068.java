class DummyClass_96068 {
  @Test
  public void testOverwrite() {
    String str = "I like Ontario Bank and Ontario Lake , and I like the Native American Church , too .";
    Annotation document = createDocument(str);
    annotator.annotate(document);
    List<CoreLabel> tokens = document.get(CoreAnnotations.TokensAnnotation.class);

    checkNerTags(tokens, "O", "O", "ORGANIZATION", "ORGANIZATION", "O", "STATE_OR_PROVINCE", "LOCATION", "O", "O", "O", "O", "O", "RELIGION",
      "RELIGION", "RELIGION", "O", "O", "O");

  }

}