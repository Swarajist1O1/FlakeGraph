class DummyClass_94694 {
  @Test public void responseCacheRequestHeaders() throws IOException, URISyntaxException {
      @Override public CacheResponse get(URI uri, String requestMethod,
          Map<String, List<String>> requestHeaders) throws IOException {
        requestHeadersRef.set(requestHeaders);
        return null;
      }

}