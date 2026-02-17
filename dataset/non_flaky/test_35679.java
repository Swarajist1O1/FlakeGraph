class DummyClass_35679 {
  @Test
  public void testBasicSort() throws Exception {
    String topic = "testPipeline";
    LoggerContext loggerContext = LogPipelineTestUtil.createLoggerContext("WARN",
                                                                          ImmutableMap.of("test.logger", "INFO"),
                                                                          MockAppender.class.getName());
    final MockAppender appender = LogPipelineTestUtil.getAppender(loggerContext.getLogger(Logger.ROOT_LOGGER_NAME),
                                                                  "Test", MockAppender.class);
    TestCheckpointManager checkpointManager = new TestCheckpointManager();
    KafkaPipelineConfig config = new KafkaPipelineConfig(topic, Collections.singleton(0), 1024L, 300L, 1048576, 500L);
    KAFKA_TESTER.createTopic(topic, 1);

    loggerContext.start();
    KafkaLogProcessorPipeline pipeline = new KafkaLogProcessorPipeline(
      new LogProcessorPipelineContext(CConfiguration.create(), "test", loggerContext, NO_OP_METRICS_CONTEXT, 0),
      checkpointManager,
      KAFKA_TESTER.getBrokerService(), config);

    pipeline.startAndWait();

    // Publish some log messages to Kafka
    long now = System.currentTimeMillis();
    publishLog(topic, ImmutableList.of(
      LogPipelineTestUtil.createLoggingEvent("test.logger", Level.INFO, "0", now - 1000),
      LogPipelineTestUtil.createLoggingEvent("test.logger", Level.INFO, "2", now - 700),
      LogPipelineTestUtil.createLoggingEvent("test.logger", Level.INFO, "3", now - 500),
      LogPipelineTestUtil.createLoggingEvent("test.logger", Level.INFO, "1", now - 900),
      LogPipelineTestUtil.createLoggingEvent("test.logger", Level.DEBUG, "hidden", now - 600),
      LogPipelineTestUtil.createLoggingEvent("test.logger", Level.INFO, "4", now - 100))
    );

    // Since the messages are published in one batch, the processor should be able to fetch all of them,
    // hence the sorting order should be deterministic.
    // The DEBUG message should get filtered out
    Tasks.waitFor(5, () -> appender.getEvents().size(), 5, TimeUnit.SECONDS, 100, TimeUnit.MILLISECONDS);

    for (int i = 0; i < 5; i++) {
      Assert.assertEquals(Integer.toString(i), appender.getEvents().poll().getMessage());
    }

    // Now publish large messages that exceed the maximum queue size (1024). It should trigger writing regardless of
    // the event timestamp
    List<ILoggingEvent> events = new ArrayList<>(500);
    now = System.currentTimeMillis();
    for (int i = 0; i < 500; i++) {
      // The event timestamp is 10 seconds in future.
      events.add(LogPipelineTestUtil.createLoggingEvent("test.large.logger",
                                                        Level.WARN, "Large logger " + i, now + 10000));
    }
    publishLog(topic, events);

    Tasks.waitFor(true, () -> !appender.getEvents().isEmpty(), 5, TimeUnit.SECONDS, 100, TimeUnit.MILLISECONDS);

    events.clear();
    events.addAll(appender.getEvents());

    for (int i = 0; i < events.size(); i++) {
      Assert.assertEquals("Large logger " + i, events.get(i).getMessage());
    }

    pipeline.stopAndWait();
    loggerContext.stop();

    Assert.assertNull(appender.getEvents());
  }

}