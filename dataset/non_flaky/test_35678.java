class DummyClass_35678 {
  @Test
  public void testSingleAppender() throws Exception {
    LoggerContext loggerContext = LogPipelineTestUtil.createLoggerContext("WARN",
                                                                          ImmutableMap.of("test.logger", "INFO"),
                                                                          MockAppender.class.getName());
    final MockAppender appender = LogPipelineTestUtil.getAppender(loggerContext.getLogger(Logger.ROOT_LOGGER_NAME),
                                                                  "Test", MockAppender.class);
    MockCheckpointManager checkpointManager = new MockCheckpointManager();
    LogBufferPipelineConfig config = new LogBufferPipelineConfig(1024L, 300L, 500L, 4);
    loggerContext.start();
    LogBufferProcessorPipeline pipeline = new LogBufferProcessorPipeline(
      new LogProcessorPipelineContext(CConfiguration.create(), "test", loggerContext, NO_OP_METRICS_CONTEXT, 0),
      config, checkpointManager, 0);
    // start the pipeline
    pipeline.startAndWait();

    // start thread to write to incomingEventQueue
    List<ILoggingEvent> events = getLoggingEvents();
    AtomicInteger i = new AtomicInteger(0);
    List<LogBufferEvent> bufferEvents = events.stream().map(event -> {
      LogBufferEvent lbe = new LogBufferEvent(event, serializer.toBytes(event).length,
                                              new LogBufferFileOffset(0, i.get()));
      i.incrementAndGet();
      return lbe;
    }).collect(Collectors.toList());

    // start a thread to send log buffer events to pipeline
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    executorService.execute(() -> {
      for (int count = 0; count < 40; count++) {
        pipeline.processLogEvents(bufferEvents.iterator());
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          // should not happen
        }
      }
    });

    // wait for pipeline to append all the logs to appender. The DEBUG message should get filtered out.
    Tasks.waitFor(200, () -> appender.getEvents().size(), 60, TimeUnit.SECONDS, 100, TimeUnit.MILLISECONDS);
    executorService.shutdown();
    pipeline.stopAndWait();
    loggerContext.stop();
  }

}