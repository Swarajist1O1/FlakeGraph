class DummyClass_96066 {
  @Test
  public void testTokensRegexCustomAnnotate() throws Exception {

    Properties props = new Properties();
    props.setProperty(REGEX_ANNOTATOR_NAME + ".mapping.header", "pattern,test,overwrite,priority,group");
    props.setProperty(REGEX_ANNOTATOR_NAME + ".mapping.field.test", "edu.stanford.nlp.pipeline.TokensRegexNERAnnotatorITest$TestAnnotation");
    String[][] regexes =
      new String[][]{
        new String[]{"test", "TEST", "", "0"}
      };
    Annotator annotatorCased = getTokensRegexNerAnnotator(props, regexes, true);

    String str = "Marking all test as test";
    Annotation document = createDocument(str);
    annotatorCased.annotate(document);
    List<CoreLabel> tokens = document.get(CoreAnnotations.TokensAnnotation.class);

    checkTags(tokens, CoreAnnotations.TextAnnotation.class, "Marking", "all", "test", "as", "test");
    checkTags(tokens, TestAnnotation.class, null, null, "TEST", null, "TEST");
  }

}