class DummyClass_94692 {
  @Test public void responseCacheReturnsNullOutputStream() throws Exception {
      @Override public CacheRequest put(URI uri, URLConnection connection) {
        return new CacheRequest() {
          @Override public void abort() {
            aborted.set(true);
          }

}