class DummyClass_94663 {
  @Test public void onlyOneLiteralHeadersFrame() throws IOException {
      @Override public void headers(boolean inFinished, int streamId,
          int associatedStreamId, List<Header> headerBlock) {
        assertTrue(inFinished);
        assertEquals(expectedStreamId, streamId);
        assertEquals(-1, associatedStreamId);
        assertEquals(sentHeaders, headerBlock);
      }

}