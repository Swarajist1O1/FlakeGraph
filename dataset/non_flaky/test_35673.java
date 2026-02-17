class DummyClass_35673 {
  @Test
  public void testWrites() throws Exception {
    CConfiguration cConf = CConfiguration.create();
    String absolutePath = TMP_FOLDER.newFolder().getAbsolutePath();
    cConf.set(Constants.LogBuffer.LOG_BUFFER_BASE_DIR, absolutePath);
    cConf.setLong(Constants.LogBuffer.LOG_BUFFER_MAX_FILE_SIZE_BYTES, 100000);

    LoggerContext loggerContext = LogPipelineTestUtil
      .createLoggerContext("WARN", ImmutableMap.of("test.logger", "INFO"), MockAppender.class.getName());
    final MockAppender appender =
      LogPipelineTestUtil.getAppender(loggerContext.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME),
                                      "Test", MockAppender.class);
    MockCheckpointManager checkpointManager = new MockCheckpointManager();
    LogBufferPipelineConfig config = new LogBufferPipelineConfig(1024L, 300L, 500L, 4);
    loggerContext.start();
    LogBufferProcessorPipeline pipeline = new LogBufferProcessorPipeline(
      new LogProcessorPipelineContext(CConfiguration.create(), "test", loggerContext, NO_OP_METRICS_CONTEXT, 0),
      config, checkpointManager, 0);
    // start the pipeline
    pipeline.startAndWait();

    ConcurrentLogBufferWriter writer = new ConcurrentLogBufferWriter(cConf, ImmutableList.of(pipeline), () -> { });
    ImmutableList<byte[]> events = getLoggingEvents();
    writer.process(new LogBufferRequest(0, events));

    // verify if the events were written to log buffer
    try (DataInputStream dis = new DataInputStream(new FileInputStream(absolutePath + "/0.buf"))) {
      for (byte[] eventBytes : events) {
        ILoggingEvent event = serializer.fromBytes(ByteBuffer.wrap(eventBytes));
        Assert.assertEquals(event.getMessage(), getEvent(dis, serializer.toBytes(event).length).getMessage());
      }
    }

    // verify if the pipeline has processed the messages.
    Tasks.waitFor(5, () -> appender.getEvents().size(), 60, TimeUnit.SECONDS, 100, TimeUnit.MILLISECONDS);
    pipeline.stopAndWait();
    loggerContext.stop();
  }

}