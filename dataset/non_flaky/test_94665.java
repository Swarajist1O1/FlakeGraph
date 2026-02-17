class DummyClass_94665 {
  @Test public void headersFrameThenContinuation() throws IOException {
      @Override public void headers(boolean inFinished, int streamId,
          int associatedStreamId, List<Header> headerBlock) {
        assertFalse(inFinished);
        assertEquals(expectedStreamId, streamId);
        assertEquals(-1, associatedStreamId);
        assertEquals(sentHeaders, headerBlock);
      }

}