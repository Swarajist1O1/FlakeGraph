class DummyClass_96089 {
  @Test
  public void testTregexJson() throws IOException {

    String expected="{\"sentences\":[{\"0\":{\"sentIndex\":0,\"characterOffsetBegin\":4,\"characterOffsetEnd\":7,\"match\":\"(NNdog)\\n\",\"spanString\":\"dog\",\"namedNodes\":[]},\"1\":{\"sentIndex\":0,\"characterOffsetBegin\":14,\"characterOffsetEnd\":18,\"match\":\"(NNfish)\\n\",\"spanString\":\"fish\",\"namedNodes\":[]}}]}".replaceAll(" ", "");

    String query = "The dog ate a fish";
    byte[] message = query.getBytes("utf-8");
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit,pos,parse");
    String queryParams = String.format("pattern=NN&properties=%s",
                                       URLEncoder.encode(PropertiesUtils.propsAsJsonString(props), "utf-8"));
    URL serverURL = new URL("http", "localhost", port, "/tregex?" + queryParams);
    String response = slurpURL(serverURL, message);

    Assert.assertEquals(expected, response.replaceAll(" ", "").replaceAll("\n", ""));
  }

}