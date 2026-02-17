class DummyClass_94686 {
  @Test public void put_httpGet() throws Exception {
      @Override public CacheRequest put(URI uri, URLConnection connection) throws IOException {
        try {
          assertTrue(connection instanceof HttpURLConnection);
          assertFalse(connection instanceof HttpsURLConnection);

          assertEquals(response.length, connection.getContentLength());

          HttpURLConnection httpUrlConnection = (HttpURLConnection) connection;
          assertEquals("GET", httpUrlConnection.getRequestMethod());
          assertTrue(httpUrlConnection.getDoInput());
          assertFalse(httpUrlConnection.getDoOutput());

          assertEquals("Fantastic", httpUrlConnection.getResponseMessage());
          assertEquals(toUri(serverUrl), uri);
          assertEquals(serverUrl, connection.getURL());
          assertEquals("value", connection.getRequestProperty("key"));

          // Check retrieval by string key.
          assertEquals(statusLine, httpUrlConnection.getHeaderField(null));
          assertEquals("c", httpUrlConnection.getHeaderField("A"));
          // The RI and OkHttp supports case-insensitive matching for this method.
          assertEquals("c", httpUrlConnection.getHeaderField("a"));
          return null;
        } catch (Throwable t) {
          throw new IOException("unexpected cache failure", t);
        }
      }

}