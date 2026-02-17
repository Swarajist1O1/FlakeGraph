class DummyClass_35699 {
  @Test
  public void testSerialize() throws Exception {
    Logger logger = LoggerFactory.getLogger(LoggingEventTest.class);
    ch.qos.logback.classic.spi.LoggingEvent iLoggingEvent = new ch.qos.logback.classic.spi.LoggingEvent(
      getClass().getName(), (ch.qos.logback.classic.Logger) logger, Level.ERROR, "Log message1", null,
      new Object[] {"arg1", "arg2", "100"});
    iLoggingEvent.setLoggerName("loggerName1");
    iLoggingEvent.setThreadName("threadName1");
    iLoggingEvent.setTimeStamp(1234567890L);
    iLoggingEvent.setLoggerContextRemoteView(new LoggerContextVO("loggerContextRemoteView",
                                                                 ImmutableMap.of("key1", "value1", "key2", "value2"),
                                                                 100000L));
    Map<String, String> mdcMap = Maps.newHashMap(ImmutableMap.of("mdck1", "mdcv1", "mdck2", "mdck2"));
    iLoggingEvent.setMDCPropertyMap(mdcMap);

    LoggingEventSerializer serializer = new LoggingEventSerializer();
    byte[] encoded = serializer.toBytes(new LogMessage(iLoggingEvent, LoggingContextAccessor.getLoggingContext()));

    ILoggingEvent decodedEvent = serializer.fromBytes(ByteBuffer.wrap(encoded));
    LoggingEventSerializerTest.assertLoggingEventEquals(iLoggingEvent, decodedEvent);
  }

}