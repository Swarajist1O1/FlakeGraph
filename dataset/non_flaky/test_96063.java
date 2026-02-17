class DummyClass_96063 {
  @Test
  public void testTokensRegexSyntax() throws Exception {
    String[][] regexes =
      new String[][]{
        new String[]{"( /University/ /of/ [ {ner:LOCATION} ] )", "SCHOOL"}
        // TODO: TokensRegex literal string patterns ignores ignoreCase settings
        //new String[]{"( University of [ {ner:LOCATION} ] )", "SCHOOL"}
    };
    Annotator annotatorCased = getTokensRegexNerAnnotator(regexes, false);

    String str = "University of Alaska is located in Alaska.";
    Annotation document = createDocument(str);
    annotatorCased.annotate(document);
    List<CoreLabel> tokens = document.get(CoreAnnotations.TokensAnnotation.class);

    checkNerTags(tokens,
      "ORGANIZATION", "ORGANIZATION", "ORGANIZATION", "O", "O", "O", "LOCATION", "O");

    reannotate(tokens, CoreAnnotations.NamedEntityTagAnnotation.class,
            "O", "O", "LOCATION", "O", "O", "O", "LOCATION", "O");
    annotatorCased.annotate(document);

    checkNerTags(tokens,
      "SCHOOL", "SCHOOL", "SCHOOL", "O", "O", "O", "LOCATION", "O");

    // Try lowercase
    Annotator annotatorCaseless = getTokensRegexNerAnnotator(regexes, true);

    str = "university of alaska is located in alaska.";
    document = createDocument(str);
    tokens = document.get(CoreAnnotations.TokensAnnotation.class);
    checkNerTags(tokens,
      "O", "O", "LOCATION", "O", "O", "O", "LOCATION", "O");
    annotatorCased.annotate(document);
    checkNerTags(tokens,
      "O", "O", "LOCATION", "O", "O", "O", "LOCATION", "O");
    annotatorCaseless.annotate(document);
    checkNerTags(tokens,
      "SCHOOL", "SCHOOL", "SCHOOL", "O", "O", "O", "LOCATION", "O");
  }

}