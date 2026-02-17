class DummyClass_35698 {
  @Test
  public void testDecodeTimestamp() throws IOException {
    long timestamp = System.currentTimeMillis();

    ch.qos.logback.classic.spi.LoggingEvent event = new ch.qos.logback.classic.spi.LoggingEvent();
    event.setLevel(Level.INFO);
    event.setLoggerName("test.logger");
    event.setMessage("Some test");
    event.setTimeStamp(timestamp);

    // Serialize it
    LoggingEventSerializer serializer = new LoggingEventSerializer();
    byte[] bytes = serializer.toBytes(event);

    // Decode timestamp
    Assert.assertEquals(timestamp, serializer.decodeEventTimestamp(ByteBuffer.wrap(bytes)));
  }

}