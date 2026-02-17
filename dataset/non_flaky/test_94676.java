class DummyClass_94676 {
  @Test public void streamIdHasReservedBit() throws IOException {
      @Override public void headers(boolean inFinished, int streamId,
          int associatedStreamId, List<Header> headerBlock) {
        assertFalse(inFinished);
        assertEquals(expectedStreamId, streamId);
        assertEquals(-1, associatedStreamId);
        assertEquals(headerEntries("foo", "barrr", "baz", "qux"), headerBlock);
      }

}