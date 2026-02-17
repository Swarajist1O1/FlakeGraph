class DummyClass_35727 {
  @Test
  public void testRollOver() throws Exception {
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
    ingestLogs(logger, 20000);
    Map<LocationIdentifier, LocationOutputStream> activeFiles = rollingAppender.getLocationManager()
      .getActiveLocations();
    Assert.assertEquals(1, activeFiles.size());
    LocationOutputStream locationOutputStream = activeFiles.get(new LocationIdentifier("testNs", "testApp"));
    Location parentDir = Locations.getParent(locationOutputStream.getLocation());
    Assert.assertEquals(10, parentDir.list().size());

    // different program should go to different directory
    addTagsToMdc("testNs", "testApp1");
    ingestLogs(logger, 20000);
    activeFiles = rollingAppender.getLocationManager().getActiveLocations();
    Assert.assertEquals(2, activeFiles.size());
    locationOutputStream = activeFiles.get(new LocationIdentifier("testNs", "testApp1"));
    parentDir = Locations.getParent(locationOutputStream.getLocation());
    Assert.assertEquals(10, parentDir.list().size());

    // different program should go to different directory because namespace is different
    addTagsToMdc("testNs1", "testApp1");
    ingestLogs(logger, 20000);
    activeFiles = rollingAppender.getLocationManager().getActiveLocations();
    Assert.assertEquals(3, activeFiles.size());
    locationOutputStream = activeFiles.get(new LocationIdentifier("testNs1", "testApp1"));
    parentDir = Locations.getParent(locationOutputStream.getLocation());
    Assert.assertEquals(10, parentDir.list().size());
  }

}