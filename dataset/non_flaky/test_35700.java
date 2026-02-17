class DummyClass_35700 {
  @Test
  public void testEmptySerialize() throws Exception {
    Logger logger = LoggerFactory.getLogger(LoggingEventTest.class);
    ch.qos.logback.classic.spi.LoggingEvent iLoggingEvent = new ch.qos.logback.classic.spi.LoggingEvent(
      getClass().getName(), (ch.qos.logback.classic.Logger) logger, Level.ERROR, null, null, null);

    LoggingEventSerializer serializer = new LoggingEventSerializer();
    byte[] encoded = serializer.toBytes(new LogMessage(iLoggingEvent, LoggingContextAccessor.getLoggingContext()));

    ILoggingEvent decodedEvent = serializer.fromBytes(ByteBuffer.wrap(encoded));
    LoggingEventSerializerTest.assertLoggingEventEquals(iLoggingEvent, decodedEvent);
  }

}