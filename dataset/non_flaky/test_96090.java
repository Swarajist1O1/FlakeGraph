class DummyClass_96090 {
  @Test
  public void testSemgrexJson() throws IOException {
    String expected="{ \"sentences\": [ { \"0\": { \"text\": \"ate\", \"begin\": 2, \"end\": 3, \"$obj\": { \"text\": \"fish\", \"begin\": 4, \"end\": 5 }, \"$verb\": { \"text\": \"ate\", \"begin\": 2, \"end\": 3 } }, \"length\": 1 }  ]}".replaceAll(" ", "");

    String query = "The dog ate a fish";
    byte[] message = query.getBytes("utf-8");
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize,ssplit,pos,parse");
    String queryParams = String.format("pattern=%s&properties=%s",
                                       URLEncoder.encode("{}=verb >obj {}=obj", "utf-8"),
                                       URLEncoder.encode(PropertiesUtils.propsAsJsonString(props), "utf-8"));
    URL serverURL = new URL("http", "localhost", port, "/semgrex?" + queryParams);
    String response = slurpURL(serverURL, message);

    Assert.assertEquals(expected, response.replaceAll(" ", "").replaceAll("\n", ""));
  }

}