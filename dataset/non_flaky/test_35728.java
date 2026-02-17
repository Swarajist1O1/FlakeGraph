class DummyClass_35728 {
  @Test
  public void testFileClose() throws Exception {
    // assume SLF4J is bound to logback in the current environment
    AppenderContext appenderContext = new LocalAppenderContext(injector.getInstance(TransactionRunner.class),
                                                               injector.getInstance(LocationFactory.class),
                                                               new NoOpMetricsCollectionService());

    JoranConfigurator configurator = new JoranConfigurator();
    configurator.setContext(appenderContext);
    // Call context.reset() to clear any previous configuration, e.g. default
    // configuration. For multi-step configuration, omit calling context.reset().
    appenderContext.reset();

    configurator.doConfigure(getClass().getResourceAsStream("/rolling-appender-logback-test.xml"));
    StatusPrinter.printInCaseOfErrorsOrWarnings(appenderContext);

    RollingLocationLogAppender rollingAppender =
      (RollingLocationLogAppender) appenderContext.getLogger(RollingLocationLogAppenderTest.class)
        .getAppender("rollingAppender");

    addTagsToMdc("testNs", "testApp");
    Logger logger = appenderContext.getLogger(RollingLocationLogAppenderTest.class);
    ingestLogs(logger, 20);

    // wait for 500 ms so that file is eligible for closing
    Thread.sleep(500);
    // flush to make sure file is closed
    rollingAppender.flush();
    Assert.assertEquals(0, rollingAppender.getLocationManager().getActiveLocations().size());
  }

}