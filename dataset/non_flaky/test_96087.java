class DummyClass_96087 {
  @Test
  public void testClient() {
    String query = "The dog ate a fish";
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit,pos,parse");
    StanfordCoreNLPClient client = new StanfordCoreNLPClient(props, "http://localhost", port);
    // if something goes wrong, we don't want the unittest waiting forever for a response
    client.setTimeoutMilliseconds(30 * 1000);
    Annotation annotation = client.process(query);
    Throwable t = annotation.get(CoreAnnotations.ExceptionAnnotation.class);
    Assert.assertNull(t);

    List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
    Assert.assertEquals(1, sentences.size());
  }

}