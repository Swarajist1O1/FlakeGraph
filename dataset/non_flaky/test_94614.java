class DummyClass_94614 {
  @Test public void redirectWithProxySelector() throws Exception {
          @Override public List<Proxy> select(URI uri) {
            proxySelectionRequests.add(uri);
            MockWebServer proxyServer = (uri.getPort() == server.getPort())
                ? server
                : server2;
            return Arrays.asList(proxyServer.toProxyAddress());
          }

}