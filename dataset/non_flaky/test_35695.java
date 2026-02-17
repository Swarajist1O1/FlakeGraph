class DummyClass_35695 {
  @Test
  public void testSerialization() throws Exception {
    Map<String, String> mdcMap = Maps.newHashMap();
    mdcMap.put("mdc1", "mdc-val1");
    mdcMap.put("mdc2", null);
    mdcMap.put(null, null);

    Map<String, String> contextMap = Maps.newHashMap();
    contextMap.put("p1", "ctx-val1");
    contextMap.put("p2", null);
    contextMap.put(null, null);

    LoggingEventSerializer serializer = new LoggingEventSerializer();
    ch.qos.logback.classic.spi.LoggingEvent iLoggingEvent = new ch.qos.logback.classic.spi.LoggingEvent();
    iLoggingEvent.setThreadName("threadName1");
    iLoggingEvent.setLevel(Level.INFO);
    iLoggingEvent.setMessage("Log message1");
    iLoggingEvent.setArgumentArray(new Object[]{null, "arg2", "100", null});
    iLoggingEvent.setLoggerName("loggerName1");

    iLoggingEvent.setLoggerContextRemoteView(new LoggerContextVO("logger_context1", contextMap, 12345634234L));

    Exception e1 = new Exception(null, null);
    Exception e2 = new Exception("Test Exception2", e1);
    iLoggingEvent.setThrowableProxy(new ThrowableProxy(e2));
    iLoggingEvent.prepareForDeferredProcessing();
    ((ThrowableProxy) iLoggingEvent.getThrowableProxy()).calculatePackagingData();

    iLoggingEvent.setCallerData(new StackTraceElement[]{
      new StackTraceElement("com.Class1", "methodName1", "fileName1", 10),
      null,
      new StackTraceElement("com.Class2", "methodName2", "fileName2", 20),
      new StackTraceElement("com.Class3",  "methodName3", null, 30),
      null
    });

    iLoggingEvent.setMarker(null);
    iLoggingEvent.getMDCPropertyMap().putAll(mdcMap);
    iLoggingEvent.setTimeStamp(1234567890L);

    // Serialize
    ILoggingEvent event = new LogMessage(iLoggingEvent, LoggingContextAccessor.getLoggingContext());
    byte [] serializedBytes = serializer.toBytes(event);

    // De-serialize
    ILoggingEvent actualEvent = serializer.fromBytes(ByteBuffer.wrap(serializedBytes));
    System.out.println(actualEvent);
    assertLoggingEventEquals(iLoggingEvent, actualEvent);
  }

}