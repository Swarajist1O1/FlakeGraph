class DummyClass_35682 {
  @Test
  public void testMultiAppenders() throws Exception {
    final File logDir = TEMP_FOLDER.newFolder();
    LoggerContext loggerContext = new LoggerContext();
    loggerContext.putProperty("logDirectory", logDir.getAbsolutePath());

    LogPipelineConfigurator configurator = new LogPipelineConfigurator(CConfiguration.create());
    configurator.setContext(loggerContext);
    URL configURL = getClass().getClassLoader().getResource("pipeline-multi-appenders.xml");
    Assert.assertNotNull(configURL);
    configurator.doConfigure(configURL);

    String topic = "testMultiAppenders";
    TestCheckpointManager checkpointManager = new TestCheckpointManager();
    KafkaPipelineConfig config = new KafkaPipelineConfig(topic, Collections.singleton(0), 1024L, 100L, 1048576, 200L);
    KAFKA_TESTER.createTopic(topic, 1);

    loggerContext.start();
    KafkaLogProcessorPipeline pipeline = new KafkaLogProcessorPipeline(
      new LogProcessorPipelineContext(CConfiguration.create(), "testMultiAppenders",
                                      loggerContext, NO_OP_METRICS_CONTEXT, 0),
      checkpointManager,
      KAFKA_TESTER.getBrokerService(), config);

    pipeline.startAndWait();

    // Publish some log messages to Kafka using a non-specific logger
    long now = System.currentTimeMillis();
    publishLog(topic, ImmutableList.of(
      LogPipelineTestUtil.createLoggingEvent("logger.trace", Level.TRACE, "TRACE", now - 1000),
      LogPipelineTestUtil.createLoggingEvent("logger.debug", Level.DEBUG, "DEBUG", now - 900),
      LogPipelineTestUtil.createLoggingEvent("logger.info", Level.INFO, "INFO", now - 800),
      LogPipelineTestUtil.createLoggingEvent("logger.warn", Level.WARN, "WARN", now - 700),
      LogPipelineTestUtil.createLoggingEvent("logger.error", Level.ERROR, "ERROR", now - 600)
    ));

    // All logs should get logged to the default.log file
    Tasks.waitFor(true, () -> {
      File logFile = new File(logDir, "default.log");
      List<String> lines = Files.readAllLines(logFile.toPath(), StandardCharsets.UTF_8);
      return Arrays.asList("TRACE", "DEBUG", "INFO", "WARN", "ERROR").equals(lines);
    }, 5, TimeUnit.SECONDS, 100, TimeUnit.MILLISECONDS);

    // Publish some more log messages via the non-additive "test.info" logger.
    now = System.currentTimeMillis();
    publishLog(topic, ImmutableList.of(
      LogPipelineTestUtil.createLoggingEvent("test.info.trace", Level.TRACE, "TRACE", now - 1000),
      LogPipelineTestUtil.createLoggingEvent("test.info.debug", Level.DEBUG, "DEBUG", now - 900),
      LogPipelineTestUtil.createLoggingEvent("test.info", Level.INFO, "INFO", now - 800),
      LogPipelineTestUtil.createLoggingEvent("test.info.warn", Level.WARN, "WARN", now - 700),
      LogPipelineTestUtil.createLoggingEvent("test.info.error", Level.ERROR, "ERROR", now - 600)
    ));

    // Only logs with INFO or above level should get written to the info.log file
    Tasks.waitFor(true, () -> {
      File logFile = new File(logDir, "info.log");
      List<String> lines = Files.readAllLines(logFile.toPath(), StandardCharsets.UTF_8);
      return Arrays.asList("INFO", "WARN", "ERROR").equals(lines);
    }, 5, TimeUnit.SECONDS, 100, TimeUnit.MILLISECONDS);

    // The default.log file shouldn't be changed, because the test.info logger is non additive
    File defaultLogFile = new File(logDir, "default.log");
    List<String> lines = Files.readAllLines(defaultLogFile.toPath(), StandardCharsets.UTF_8);
    Assert.assertEquals(Arrays.asList("TRACE", "DEBUG", "INFO", "WARN", "ERROR"), lines);

    // Publish a log messages via the additive "test.error" logger.
    now = System.currentTimeMillis();
    publishLog(topic, ImmutableList.of(
      LogPipelineTestUtil.createLoggingEvent("test.error.1.2", Level.ERROR, "ERROR", now - 1000)
    ));

    // Expect the log get appended to both the error.log file as well as the default.log file
    Tasks.waitFor(true, () -> {
      File logFile = new File(logDir, "error.log");
      List<String> lines1 = Files.readAllLines(logFile.toPath(), StandardCharsets.UTF_8);
      if (!Collections.singletonList("ERROR").equals(lines1)) {
        return false;
      }

      logFile = new File(logDir, "default.log");
      lines1 = Files.readAllLines(logFile.toPath(), StandardCharsets.UTF_8);
      return Arrays.asList("TRACE", "DEBUG", "INFO", "WARN", "ERROR", "ERROR").equals(lines1);
    }, 5, TimeUnit.SECONDS, 100, TimeUnit.MILLISECONDS);

    pipeline.stopAndWait();
    loggerContext.stop();
  }

}