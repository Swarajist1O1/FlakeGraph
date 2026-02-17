class DummyClass_35697 {
  @Test
  public void testNullSerialization() throws Exception {
    Logger logger = LoggerFactory.getLogger(LoggingEventSerializerTest.class);
    LoggingEventSerializer serializer = new LoggingEventSerializer();
    ch.qos.logback.classic.spi.LoggingEvent iLoggingEvent = new ch.qos.logback.classic.spi.LoggingEvent(
      null, (ch.qos.logback.classic.Logger) logger, null, null, null, null);
    iLoggingEvent.setThreadName(null);
    iLoggingEvent.setLevel(null);
    iLoggingEvent.setMessage(null);
    iLoggingEvent.setArgumentArray(null);
    iLoggingEvent.setLoggerName(null);
    iLoggingEvent.setLoggerContextRemoteView(null);
    iLoggingEvent.setThrowableProxy(null);
    iLoggingEvent.setCallerData(null);
    iLoggingEvent.setMarker(null);
    iLoggingEvent.setMDCPropertyMap(null);
    iLoggingEvent.setTimeStamp(10000000L);

    // Serialize
    ILoggingEvent event = new LogMessage(iLoggingEvent, LoggingContextAccessor.getLoggingContext());
    byte [] serializedBytes = serializer.toBytes(event);

    // De-serialize
    ILoggingEvent actualEvent = serializer.fromBytes(ByteBuffer.wrap(serializedBytes));

    iLoggingEvent.setLevel(Level.ERROR);
    assertLoggingEventEquals(iLoggingEvent, actualEvent);
  }

}