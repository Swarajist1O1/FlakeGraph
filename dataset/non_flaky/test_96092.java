class DummyClass_96092 {
  @Test
  public void testSemgrexFilter() throws IOException {
    String expected="{ \"sentences\": [ true, false ]}".replaceAll(" ", "");

    String query = "The dog ate a fish.  He went home.";
    byte[] message = query.getBytes("utf-8");
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit,pos,parse");
    String queryParams = String.format("pattern=%s&properties=%s&filter=true",
                                       URLEncoder.encode("{}=verb >obj {}=obj", "utf-8"),
                                       URLEncoder.encode(PropertiesUtils.propsAsJsonString(props), "utf-8"));
    URL serverURL = new URL("http", "localhost", port, "/semgrex?" + queryParams);
    String response = slurpURL(serverURL, message);

    Assert.assertEquals(expected, response.replaceAll(" ", "").replaceAll("\n", ""));
  }

}