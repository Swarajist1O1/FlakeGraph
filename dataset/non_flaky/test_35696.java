class DummyClass_35696 {
  @Test
  public void testOldSystemLoggingContext() throws Exception {
    // see: CDAP-7482
    Map<String, String> mdcMap = new HashMap<>();
    mdcMap.put(ServiceLoggingContext.TAG_SYSTEM_ID, "ns1");
    mdcMap.put(ComponentLoggingContext.TAG_COMPONENT_ID, "comp1");
    mdcMap.put(ServiceLoggingContext.TAG_SERVICE_ID, "ser1");

    ch.qos.logback.classic.spi.LoggingEvent iLoggingEvent = new ch.qos.logback.classic.spi.LoggingEvent();
    iLoggingEvent.setCallerData(new StackTraceElement[] { null });
    iLoggingEvent.setMDCPropertyMap(mdcMap);

    Assert.assertTrue(LoggingContextHelper.getLoggingContext(iLoggingEvent.getMDCPropertyMap())
                        instanceof ServiceLoggingContext);
  }

}