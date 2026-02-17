class DummyClass_94684 {
  @Test public void get_httpGet() throws Exception {
      @Override public CacheResponse get(
          URI uri, String method, Map<String, List<String>> headers) throws IOException {
        try {
          assertEquals(toUri(serverUrl), uri);
          assertEquals("GET", method);
          assertTrue("Arbitrary standard header not present", headers.containsKey("User-Agent"));
          assertEquals(Collections.singletonList("value1"), headers.get("key1"));
          return null;
        } catch (Throwable t) {
          throw new IOException("unexpected cache failure", t);
        }
      }

}