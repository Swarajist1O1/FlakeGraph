class DummyClass_96064 {
  @Test
  public void testTokensRegexMatchGroup() throws Exception {
    String[][] regexes =
      new String[][]{
        new String[]{"( /the/? /movie/ (/[A-Z].*/+) )", "MOVIE", "", "0", "1"}
      };
    Annotator annotatorCased = getTokensRegexNerAnnotator(regexes, false);

    String str = "the movie Mud was very muddy";
    Annotation document = createDocument(str);
    annotatorCased.annotate(document);
    List<CoreLabel> tokens = document.get(CoreAnnotations.TokensAnnotation.class);

    checkNerTags(tokens,
      "O", "O", "MOVIE", "O", "O", "O");

  }

}