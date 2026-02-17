class DummyClass_94604 {
  @Test
  public void testURLFilterRedirect() throws Exception {
    MockWebServer cleartextServer = new MockWebServer();
    cleartextServer.enqueue(new MockResponse()
        .setBody("Blocked!"));
    final URL blockedURL = cleartextServer.url("/").url();

    SslClient contextBuilder = SslClient.localhost();
    server.useHttps(contextBuilder.socketFactory, false);
    factory.setClient(factory.client().newBuilder()
        .sslSocketFactory(contextBuilder.socketFactory, contextBuilder.trustManager)
        .followSslRedirects(true)
        .build());
    factory.setUrlFilter(new URLFilter() {
      @Override
      public void checkURLPermitted(URL url) throws IOException {
        if (blockedURL.equals(url)) {
          throw new IOException("Blocked");
        }
      }

}