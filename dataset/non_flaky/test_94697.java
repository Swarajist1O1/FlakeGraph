class DummyClass_94697 {
  @Test public void createOkResponseForCacheGet_secure() throws Exception {
      @Override public InputStream getBody() throws IOException {
        return new ByteArrayInputStream("HelloWorld".getBytes(StandardCharsets.UTF_8));
      }

}