class DummyClass_96091 {
  @Test
  public void testSemgrexAnnotation() throws IOException {
    String expected = "result { result { match { matchIndex: 3 node { name: \"obj\" matchIndex: 5 } node { name: \"verb\" matchIndex: 3 } } }}".replaceAll(" ", "");
    String query = "The dog ate a fish";
    byte[] message = query.getBytes("utf-8");
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit,pos,parse");
    String queryParams = String.format("pattern=%s&properties=%s&outputFormat=serialized",
                                       URLEncoder.encode("{}=verb >obj {}=obj", "utf-8"),
                                       URLEncoder.encode(PropertiesUtils.propsAsJsonString(props), "utf-8"));
    URL serverURL = new URL("http", "localhost", port, "/semgrex?" + queryParams);
    InputStream is = postURL(serverURL, message);
    CoreNLPProtos.SemgrexResponse response = CoreNLPProtos.SemgrexResponse.parseFrom(is);

    Assert.assertEquals(expected, response.toString().replaceAll(" ", "").replaceAll("\n", ""));
  }

}