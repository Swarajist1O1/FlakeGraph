class DummyClass_94672 {
  @Test public void maxLengthDataFrame() throws IOException {
      @Override public void data(boolean inFinished, int streamId, BufferedSource source,
          int length) throws IOException {
        assertFalse(inFinished);
        assertEquals(expectedStreamId, streamId);
        assertEquals(Http2.INITIAL_MAX_FRAME_SIZE, length);
        ByteString data = source.readByteString(length);
        for (byte b : data.toByteArray()) {
          assertEquals(2, b);
        }
      }

}