class DummyClass_35680 {
  @Test
  public void testRegularFlush() throws Exception {
    String topic = "testFlush";
    LoggerContext loggerContext = LogPipelineTestUtil.createLoggerContext("WARN",
                                                                          ImmutableMap.of("test.logger", "INFO"),
                                                                          MockAppender.class.getName());
    final MockAppender appender = LogPipelineTestUtil.getAppender(loggerContext.getLogger(Logger.ROOT_LOGGER_NAME),
                                                                  "Test", MockAppender.class);
    TestCheckpointManager checkpointManager = new TestCheckpointManager();

    // Use a longer checkpoint time and a short event delay. Should expect flush called at least once
    // per event delay.
    KafkaPipelineConfig config = new KafkaPipelineConfig(topic, Collections.singleton(0), 1024, 100, 1048576, 2000);
    KAFKA_TESTER.createTopic(topic, 1);

    loggerContext.start();
    KafkaLogProcessorPipeline pipeline = new KafkaLogProcessorPipeline(
      new LogProcessorPipelineContext(CConfiguration.create(), "test", loggerContext, NO_OP_METRICS_CONTEXT, 0),
      checkpointManager,
      KAFKA_TESTER.getBrokerService(), config);

    pipeline.startAndWait();

    // Even when there is no event, the flush should still get called.
    Tasks.waitFor(5, appender::getFlushCount, 3, TimeUnit.SECONDS, 100, TimeUnit.MILLISECONDS);

    // Publish some logs
    long now = System.currentTimeMillis();
    publishLog(topic, ImmutableList.of(
      LogPipelineTestUtil.createLoggingEvent("test.logger", Level.INFO, "0", now - 500),
      LogPipelineTestUtil.createLoggingEvent("test.logger", Level.INFO, "1", now - 300),
      LogPipelineTestUtil.createLoggingEvent("test.logger", Level.INFO, "2", now + 100)
    ));

    // Wait until getting all logs.
    Tasks.waitFor(3, () -> appender.getEvents().size(), 3, TimeUnit.SECONDS, 200, TimeUnit.MILLISECONDS);

    pipeline.stopAndWait();

    // Should get at least 20 flush calls, since the checkpoint is every 2 seconds
    Assert.assertTrue(appender.getFlushCount() >= 20);
  }

}