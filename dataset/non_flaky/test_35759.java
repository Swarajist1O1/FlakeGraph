class DummyClass_35759 {
  @Test (timeout = 5000L)
  public void testExpectContinue() throws Exception {
    URL url = new URL(resolveURI("/v2/upload"));
    HttpURLConnection urlConn = openURL(url);
    urlConn.setRequestMethod("POST");
    urlConn.setRequestProperty(HttpHeaderNames.EXPECT.toString(), HttpHeaderValues.CONTINUE.toString());
    urlConn.setDoOutput(true);

    // Forces sending small chunks to have the netty server receives multiple chunks
    urlConn.setChunkedStreamingMode(10);
    String msg = Strings.repeat("Message", 100);
    urlConn.getOutputStream().write(msg.getBytes(StandardCharsets.UTF_8));

    Assert.assertEquals(200, urlConn.getResponseCode());
    String result = new String(ByteStreams.toByteArray(urlConn.getInputStream()), StandardCharsets.UTF_8);
    Assert.assertEquals(msg, result);
  }

}