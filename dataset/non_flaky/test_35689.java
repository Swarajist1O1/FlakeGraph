class DummyClass_35689 {
  @Test
  public void test() throws Exception {
    LoggerContext loggerContext = LogPipelineTestUtil.createLoggerContext("WARN",
                                                                          ImmutableMap.of("test.logger", "INFO"),
                                                                          MockAppender.class.getName());
    LogProcessorPipelineContext context = new LogProcessorPipelineContext(CConfiguration.create(),
                                                                          "test", loggerContext, NO_OP_METRICS_CONTEXT,
                                                                          0);
    context.start();
    TimeEventQueueProcessor<TestOffset> processor = new TimeEventQueueProcessor<>(context, 50, 1,
                                                                                  ImmutableList.of(0));
    long now = System.currentTimeMillis();
    List<ILoggingEvent> events = ImmutableList.of(
      LogPipelineTestUtil.createLoggingEvent("test.logger", Level.INFO, "1", now - 1000),
      LogPipelineTestUtil.createLoggingEvent("test.logger", Level.INFO, "3", now - 700),
      LogPipelineTestUtil.createLoggingEvent("test.logger", Level.INFO, "5", now - 500),
      LogPipelineTestUtil.createLoggingEvent("test.logger", Level.INFO, "2", now - 900),
      LogPipelineTestUtil.createLoggingEvent("test.logger", Level.ERROR, "4", now - 600),
      LogPipelineTestUtil.createLoggingEvent("test.logger", Level.INFO, "6", now - 100));

    ProcessedEventMetadata<TestOffset> metadata = processor.process(0, new TransformingIterator(events.iterator()));
    // all 6 events should be processed. This is because when the buffer is full after 5 events, time event queue
    // processor should append existing buffered events and enqueue 6th event
    Assert.assertEquals(6, metadata.getTotalEventsProcessed());
    for (Map.Entry<Integer, Checkpoint<TestOffset>> entry : metadata.getCheckpoints().entrySet()) {
      Checkpoint<TestOffset> value = entry.getValue();
      // offset should be max offset processed so far
      Assert.assertEquals(6, value.getOffset().getOffset());
    }
  }

}