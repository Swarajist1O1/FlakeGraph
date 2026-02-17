class DummyClass_94691 {
  @Test public void responseCacheCallbackApis() throws Exception {
      @Override public CacheRequest put(URI uri, URLConnection connection) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
        assertEquals(server.url("/").url(), uri.toURL());
        assertEquals(200, httpURLConnection.getResponseCode());
        InputStream is = httpURLConnection.getInputStream();
        try {
          is.read();
          fail();
        } catch (UnsupportedOperationException expected) {
        }
        assertEquals("5", connection.getHeaderField("Content-Length"));
        assertEquals("text/plain", connection.getHeaderField("Content-Type"));
        assertEquals("ijk", connection.getHeaderField("fgh"));
        cacheCount.incrementAndGet();
        return null;
      }

}