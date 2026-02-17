class DummyClass_35757 {
  @Test
  public void testConnectionIdleTimeout() throws Exception {
    // Only use server1
    defaultServer2.cancelRegistration();

    String path = "/v2/ping";
    URI uri = new URI(resolveURI(path));
    Socket socket = getSocketFactory().createSocket(uri.getHost(), uri.getPort());
    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    InputStream inputStream = socket.getInputStream();

    // make a request
    String firstLine = makeRequest(uri, out, inputStream);
    Assert.assertEquals("HTTP/1.1 200 OK", firstLine);

    // sleep for 500 ms below the configured idle timeout; the connection on server side should not get closed by then
    // Hence it should be reusing the same server side connection
    TimeUnit.MILLISECONDS.sleep(TimeUnit.SECONDS.toMillis(CONNECTION_IDLE_TIMEOUT_SECS) - 500);
    firstLine = makeRequest(uri, out, inputStream);
    Assert.assertEquals("HTTP/1.1 200 OK", firstLine);

    // sleep for 500 ms over the configured idle timeout; the connection on server side should get closed by then
    // Hence it should be create a new server side connection
    TimeUnit.MILLISECONDS.sleep(TimeUnit.SECONDS.toMillis(CONNECTION_IDLE_TIMEOUT_SECS) + 500);
    // Due to timeout the client connection will be closed, and hence this request should not go to the server
    firstLine = makeRequest(uri, out, inputStream);
    Assert.assertEquals("HTTP/1.1 200 OK", firstLine);

    // assert that the connection is closed on the server side
    Assert.assertEquals(3, defaultServer1.getNumRequests());
    Assert.assertEquals(2, defaultServer1.getNumConnectionsOpened());
    Assert.assertEquals(1, defaultServer1.getNumConnectionsClosed());
  }

}