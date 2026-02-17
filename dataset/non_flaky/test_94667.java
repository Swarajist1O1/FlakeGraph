class DummyClass_94667 {
  @Test public void pushPromiseThenContinuation() throws IOException {
      public void pushPromise(int streamId, int promisedStreamId, List<Header> headerBlock) {
        assertEquals(expectedStreamId, streamId);
        assertEquals(expectedPromisedStreamId, promisedStreamId);
        assertEquals(pushPromise, headerBlock);
      }

}