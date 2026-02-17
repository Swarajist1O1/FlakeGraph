class DummyClass_96065 {
  @Test
  public void testTokensRegexNormalizedAnnotate() throws Exception {
    Properties props = new Properties();
    props.setProperty(REGEX_ANNOTATOR_NAME + ".mapping.header", "pattern,ner,normalized,overwrite,priority,group");

    String[][] regexes =
      new String[][]{
        new String[]{"blue",  "COLOR", "B", "", "0"},
        new String[]{"red",   "COLOR", "R", "", "0"},
        new String[]{"green", "COLOR", "G", "", "0"}
      };
    Annotator annotatorCased = getTokensRegexNerAnnotator(props, regexes, false);

    String str = "These are all colors: blue, red, and green.";
    Annotation document = createDocument(str);
    annotatorCased.annotate(document);
    List<CoreLabel> tokens = document.get(CoreAnnotations.TokensAnnotation.class);

    checkTags(tokens, CoreAnnotations.TextAnnotation.class, "These", "are", "all", "colors", ":", "blue", ",", "red", ",", "and", "green", ".");
    checkTags(tokens, CoreAnnotations.NamedEntityTagAnnotation.class,  "O", "O", "O", "O", "O", "COLOR", "O", "COLOR", "O", "O", "COLOR", "O");
    checkTags(tokens, CoreAnnotations.NormalizedNamedEntityTagAnnotation.class,  null, null, null, null, null, "B", null, "R", null, null, "G", null);
  }

}