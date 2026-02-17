class DummyClass_94603 {
  @Test
  public void testURLFilter() throws Exception {
    server.enqueue(new MockResponse()
        .setBody("B"));
    final URL blockedURL = server.url("/a").url();
    factory.setUrlFilter(new URLFilter() {
      @Override
      public void checkURLPermitted(URL url) throws IOException {
        if (blockedURL.equals(url)) {
          throw new IOException("Blocked");
        }
      }

}