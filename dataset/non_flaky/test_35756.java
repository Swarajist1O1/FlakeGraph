class DummyClass_35756 {
  @Test
  public void testConnectionClose() throws Exception {
    URL[] urls = new URL[] {
      new URL(resolveURI("/abc/v1/status")),
      new URL(resolveURI("/def/v1/status"))
    };

    // Make bunch of requests to one service to 2 difference urls, with the first one keep-alive, second one not.
    // This make router creates two backend service connections on the same inbound connection
    // This is to verify on the close of the second one, it won't close the the inbound if there is an
    // in-flight request happening already (if reached another round of the following for-loop).
    int times = 1000;
    boolean keepAlive = true;
    for (int i = 0; i < times; i++) {
      HttpURLConnection urlConn = openURL(urls[i % urls.length]);
      try {
        urlConn.setRequestProperty(HttpHeaderNames.CONNECTION.toString(),
                                   (keepAlive ? HttpHeaderValues.KEEP_ALIVE : HttpHeaderValues.CLOSE).toString());
        Assert.assertEquals(HttpURLConnection.HTTP_OK, urlConn.getResponseCode());
      } finally {
        urlConn.getInputStream().close();
        keepAlive = !keepAlive;
        urlConn.disconnect();
      }
    }

    Assert.assertEquals(times, defaultServer1.getNumRequests() + defaultServer2.getNumRequests());
  }

}