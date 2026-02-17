class DummyClass_35726 {
  @Test
  public void testRollingLocationLogAppender() throws Exception {
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

    addTagsToMdc("testNamespace", "testApp");
    Logger logger = appenderContext.getLogger(RollingLocationLogAppenderTest.class);
    ingestLogs(logger, 5);
    Map<LocationIdentifier, LocationOutputStream> activeFiles = rollingAppender.getLocationManager()
      .getActiveLocations();
    Assert.assertEquals(1, activeFiles.size());
    verifyFileOutput(activeFiles, 5);

    // different program should go to different directory
    addTagsToMdc("testNamespace", "testApp1");
    ingestLogs(logger, 5);
    activeFiles = rollingAppender.getLocationManager().getActiveLocations();
    Assert.assertEquals(2, activeFiles.size());
    verifyFileOutput(activeFiles, 5);

    // different program should go to different directory because namespace is different
    addTagsToMdc("testNamespace1", "testApp1");
    ingestLogs(logger, 5);
    activeFiles = rollingAppender.getLocationManager().getActiveLocations();
    Assert.assertEquals(3, activeFiles.size());
    verifyFileOutput(activeFiles, 5);
  }

}