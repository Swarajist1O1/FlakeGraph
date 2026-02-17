class DummyClass_94649 {
  @Test public void proxySelectorReturnsNull() throws Exception {
      @Override public List<Proxy> select(URI uri) {
        assertEquals(uriHost, uri.getHost());
        return null;
      }

}