class DummyClass_96015 {
  @Test
  public void testRebuildingText() {
    // set up basic English pipeline
    Properties basicProperties = new Properties();
    basicProperties.setProperty("annotators", "tokenize,ssplit");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(basicProperties);
    String text = "Let's hope this doesn't not work properly.  Especially across sentences. ";
    CoreDocument doc = new CoreDocument(pipeline.process(text));
    String rebuiltText = SentenceUtils.listToOriginalTextString(doc.tokens());
    assertTrue(text.equals(rebuiltText));
  }

}