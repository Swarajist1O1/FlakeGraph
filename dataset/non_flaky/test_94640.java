class DummyClass_94640 {
  @Test public void proxySelector() throws Exception {
      @Override public List<Proxy> select(URI uri) {
        return Collections.singletonList(socksProxy.proxy());
      }

}