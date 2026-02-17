class DummyClass_35724 {
  @Test (timeout = 10000L)
  public void testConfigUpdate() throws Exception {
    File logbackFile = createLogbackFile(new File(TEMP_FOLDER.newFolder(), "logback.xml"), "");

    // Create a logger context from the generated logback.xml file
    LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
    loggerContext.reset();

    JoranConfigurator configurator = new JoranConfigurator();
    configurator.setContext(loggerContext);
    configurator.doConfigure(logbackFile);

    TestLogAppender testAppender = new TestLogAppender();
    testAppender.setName("TestAppender");
    LogAppenderInitializer initializer = new LogAppenderInitializer(testAppender);
    initializer.initialize();

    LoggingContextAccessor.setLoggingContext(new TestLoggingContext("ns", "app", "run", "instance"));

    Logger logger = loggerContext.getLogger(LogAppenderInitializerTest.class);
    logger.info("Testing");

    Assert.assertEquals("Testing", testAppender.getLastMessage());

    // Update the logback file
    createLogbackFile(logbackFile, "<logger name=\"io.cdap.cdap\" level=\"INFO\" />");

    // Wait till the test appender stop() is called. This will happen when logback detected the changes.
    while (!testAppender.isStoppedOnce()) {
      // We need to keep logging because there is some internal thresold in logback implementation to trigger the
      // config reload thread.
      logger.info("Waiting stop");
      TimeUnit.MILLISECONDS.sleep(150);
      // Update the last modified time of the logback file to make sure logback can pick it with.
      logbackFile.setLastModified(System.currentTimeMillis());
    }

    // The appender should get automatically started again
    Tasks.waitFor(true, testAppender::isStarted, 5, TimeUnit.SECONDS);
    logger.info("Reattached");

    Assert.assertEquals("Reattached", testAppender.getLastMessage());
    loggerContext.stop();
  }

}