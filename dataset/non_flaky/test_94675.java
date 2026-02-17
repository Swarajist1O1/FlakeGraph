class DummyClass_94675 {
  @Test public void goAwayWithDebugDataRoundTrip() throws IOException {
      @Override public void goAway(
          int lastGoodStreamId, ErrorCode errorCode, ByteString debugData) {
        assertEquals(0, lastGoodStreamId);
        assertEquals(expectedError, errorCode);
        assertEquals(expectedData, debugData);
      }

}