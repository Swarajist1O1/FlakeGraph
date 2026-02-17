class DummyClass_94673 {
  @Test public void windowUpdateRoundTrip() throws IOException {
      @Override public void windowUpdate(int streamId, long windowSizeIncrement) {
        assertEquals(expectedStreamId, streamId);
        assertEquals(expectedWindowSizeIncrement, windowSizeIncrement);
      }

}