class DummyClass_94695 {
  @Test public void createOkResponseForCacheGet() throws Exception {
      @Override public InputStream getBody() throws IOException {
        return new ByteArrayInputStream("HelloWorld".getBytes(StandardCharsets.UTF_8));
      }

}