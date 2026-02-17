class DummyClass_96088 {
  @Test
  public void testClientFailure() {
    String query = "The dog ate a fish";
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit,pos,parse");
    //StanfordCoreNLPClient client = new StanfordCoreNLPClient(props, "http://localhost", port);
    StanfordCoreNLPClient client = new StanfordCoreNLPClient(props, "localhost", port);
    client.setTimeoutMilliseconds(1000);
    Annotation annotation = client.process(query);
    Throwable t = annotation.get(CoreAnnotations.ExceptionAnnotation.class);
    Assert.assertNotNull(t);
  }

}