class DummyClass_94624 {
  @Test public void networkInterceptorsObserveNetworkHeaders() throws Exception {
      @Override public Response intercept(Chain chain) throws IOException {
        // The network request has everything: User-Agent, Host, Accept-Encoding.
        Request networkRequest = chain.request();
        assertNotNull(networkRequest.header("User-Agent"));
        assertEquals(server.getHostName() + ":" + server.getPort(),
            networkRequest.header("Host"));
        assertNotNull(networkRequest.header("Accept-Encoding"));

        // The network response also has everything, including the raw gzipped content.
        Response networkResponse = chain.proceed(networkRequest);
        assertEquals("gzip", networkResponse.header("Content-Encoding"));
        return networkResponse;
      }

}