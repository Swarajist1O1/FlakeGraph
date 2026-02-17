class DummyClass_94674 {
  @Test public void goAwayWithoutDebugDataRoundTrip() throws IOException {
      @Override public void goAway(
          int lastGoodStreamId, ErrorCode errorCode, ByteString debugData) {
        assertEquals(expectedStreamId, lastGoodStreamId);
        assertEquals(expectedError, errorCode);
        assertEquals(0, debugData.size());
      }

}