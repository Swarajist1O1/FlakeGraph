class DummyClass_35758 {
  @Test
  public void testConnectionIdleTimeoutWithMultipleServers() throws Exception {
    defaultServer2.cancelRegistration();

    URL url = new URL(resolveURI("/v2/ping"));
    HttpURLConnection urlConnection = openURL(url);
    Assert.assertEquals(200, urlConnection.getResponseCode());
    urlConnection.getInputStream().close();
    urlConnection.disconnect();

    // requests past this point will go to defaultServer2
    defaultServer1.cancelRegistration();
    defaultServer2.registerServer();

    for (int i = 0; i < 4; i++) {
      // this is an assumption that CONNECTION_IDLE_TIMEOUT_SECS is more than 1 second
      TimeUnit.SECONDS.sleep(1);
      url = new URL(resolveURI("/v1/ping/" + i));
      urlConnection = openURL(url);
      Assert.assertEquals(200, urlConnection.getResponseCode());
      urlConnection.getInputStream().close();
      urlConnection.disconnect();
    }

    // for the past 4 seconds, we've been making requests to defaultServer2; therefore, defaultServer1 will have closed
    // its single connection
    Assert.assertEquals(1, defaultServer1.getNumConnectionsOpened());
    Assert.assertEquals(1, defaultServer1.getNumConnectionsClosed());

    // however, the connection to defaultServer2 is not timed out, because we've been making requests to it
    Assert.assertEquals(1, defaultServer2.getNumConnectionsOpened());
    Assert.assertEquals(0, defaultServer2.getNumConnectionsClosed());

    defaultServer2.registerServer();
    defaultServer1.cancelRegistration();
    url = new URL(resolveURI("/v2/ping"));
    urlConnection = openURL(url);
    Assert.assertEquals(200, urlConnection.getResponseCode());
    urlConnection.getInputStream().close();
    urlConnection.disconnect();
  }

}