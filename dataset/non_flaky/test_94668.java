class DummyClass_94668 {
  @Test public void readRstStreamFrame() throws IOException {
      @Override public void rstStream(int streamId, ErrorCode errorCode) {
        assertEquals(expectedStreamId, streamId);
        assertEquals(ErrorCode.PROTOCOL_ERROR, errorCode);
      }

}