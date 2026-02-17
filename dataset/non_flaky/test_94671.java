class DummyClass_94671 {
  @Test public void pingRoundTrip() throws IOException {
      @Override public void ping(boolean ack, int payload1, int payload2) {
        assertTrue(ack);
        assertEquals(expectedPayload1, payload1);
        assertEquals(expectedPayload2, payload2);
      }

}