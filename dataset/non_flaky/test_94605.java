class DummyClass_94605 {
  @Test @Ignore public void lenientUrlToUriNul() throws Exception {
      @Override public Response get(Request request) throws IOException {
        uriReference.set(request.url().uri());
        throw new UnsupportedOperationException();
      }

}