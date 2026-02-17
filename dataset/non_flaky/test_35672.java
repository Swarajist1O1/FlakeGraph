class DummyClass_35672 {
  @Test
  public void testLogBufferRecoveryService() throws Exception {
    String absolutePath = TMP_FOLDER.newFolder().getAbsolutePath();

    // create and start pipeline
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

    // write directly to log buffer
    LogBufferWriter writer = new LogBufferWriter(absolutePath, 250, () -> { });
    ImmutableList<byte[]> events = getLoggingEvents();
    writer.write(events.iterator()).iterator();
    writer.close();

    // start log buffer reader to read log events from files. keep the batch size as 2 so that there are more than 1
    // iterations
    LogBufferRecoveryService service = new LogBufferRecoveryService(ImmutableList.of(pipeline),
                                                                    ImmutableList.of(checkpointManager),
                                                                    absolutePath, 2, new AtomicBoolean(true));
    service.startAndWait();

    Tasks.waitFor(5, () -> appender.getEvents().size(), 120, TimeUnit.SECONDS, 100, TimeUnit.MILLISECONDS);

    service.stopAndWait();
    pipeline.stopAndWait();
    loggerContext.stop();
  }

}