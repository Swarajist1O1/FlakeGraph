class DummyClass_94678 {
  @Test public void blockedStreamDoesntStarveNewStream() throws Exception {
    @Override public boolean onRequest(int streamId, List<Header> requestHeaders) {
      return false;
    }

}