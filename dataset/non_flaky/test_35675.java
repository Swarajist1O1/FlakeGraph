class DummyClass_35675 {
  @Test
  public void testHandler() throws Exception {
    CConfiguration cConf = CConfiguration.create();
    String absolutePath = TMP_FOLDER.newFolder().getAbsolutePath();
    cConf.set(Constants.LogBuffer.LOG_BUFFER_BASE_DIR, absolutePath);
    cConf.setLong(Constants.LogBuffer.LOG_BUFFER_MAX_FILE_SIZE_BYTES, 100000);

    LoggerContext loggerContext = LogPipelineTestUtil
      .createLoggerContext("WARN", ImmutableMap.of("test.logger", "INFO"), MockAppender.class.getName());
    final MockAppender appender =
      LogPipelineTestUtil.getAppender(loggerContext.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME),
                                      "Test", MockAppender.class);

    LogBufferProcessorPipeline pipeline = getLogPipeline(loggerContext);
    pipeline.startAndWait();

    ConcurrentLogBufferWriter writer = new ConcurrentLogBufferWriter(cConf, ImmutableList.of(pipeline), () -> { });

    NettyHttpService httpService = NettyHttpService.builder("RemoteAppenderTest")
      .setHttpHandlers(new LogBufferHandler(writer))
      .setExceptionHandler(new HttpExceptionHandler())
      .build();

    httpService.start();

    RemoteLogAppender remoteLogAppender = getRemoteAppender(cConf, httpService);
    remoteLogAppender.start();

    List<ILoggingEvent> events = getLoggingEvents();
    WorkerLoggingContext loggingContext =
      new WorkerLoggingContext("default", "app1", "worker1", "run1", "instance1");
    for (int i = 0; i < 1000; i++) {
      remoteLogAppender.append(new LogMessage(events.get(i % events.size()), loggingContext));
    }

    Tasks.waitFor(1000, () -> appender.getEvents().size(), 120, TimeUnit.SECONDS, 100, TimeUnit.MILLISECONDS);

    remoteLogAppender.stop();
    httpService.stop();
    pipeline.stopAndWait();
    loggerContext.stop();
  }

}