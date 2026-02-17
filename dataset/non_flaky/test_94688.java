class DummyClass_94688 {
  @Test public void put_httpsGet() throws Exception {
      @Override public CacheRequest put(URI uri, URLConnection connection) throws IOException {
        try {
          assertTrue(connection instanceof HttpsURLConnection);
          assertEquals(toUri(serverUrl), uri);
          assertEquals(serverUrl, connection.getURL());

          HttpsURLConnection cacheHttpsUrlConnection = (HttpsURLConnection) connection;
          HttpsURLConnection realHttpsUrlConnection =
              (HttpsURLConnection) CacheAdapterTest.this.connection;
          assertEquals(realHttpsUrlConnection.getCipherSuite(),
              cacheHttpsUrlConnection.getCipherSuite());
          assertEquals(realHttpsUrlConnection.getPeerPrincipal(),
              cacheHttpsUrlConnection.getPeerPrincipal());
          assertArrayEquals(realHttpsUrlConnection.getLocalCertificates(),
              cacheHttpsUrlConnection.getLocalCertificates());
          assertArrayEquals(realHttpsUrlConnection.getServerCertificates(),
              cacheHttpsUrlConnection.getServerCertificates());
          assertEquals(realHttpsUrlConnection.getLocalPrincipal(),
              cacheHttpsUrlConnection.getLocalPrincipal());
          return null;
        } catch (Throwable t) {
          throw new IOException("unexpected cache failure", t);
        }
      }

}