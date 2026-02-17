class DummyClass_35694 {
  @Test
  public void testEmptySerialization() throws Exception {
    Logger logger = LoggerFactory.getLogger(LoggingEventSerializerTest.class);
    LoggingEventSerializer serializer = new LoggingEventSerializer();
    ch.qos.logback.classic.spi.LoggingEvent iLoggingEvent = new ch.qos.logback.classic.spi.LoggingEvent(
      getClass().getName(), (ch.qos.logback.classic.Logger) logger, Level.ERROR, "message", null, null);
    iLoggingEvent.setThreadName("thread-1");
    iLoggingEvent.setTimeStamp(10000000L);

    // Serialize
    ILoggingEvent event = new LogMessage(iLoggingEvent, LoggingContextAccessor.getLoggingContext());
    byte [] serializedBytes = serializer.toBytes(event);

    // De-serialize
    ILoggingEvent actualEvent = serializer.fromBytes(ByteBuffer.wrap(serializedBytes));
    assertLoggingEventEquals(iLoggingEvent, actualEvent);
  }

}