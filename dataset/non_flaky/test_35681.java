class DummyClass_35681 {
  @Test
  public void testMetricsAppender() throws Exception {
    Injector injector = KAFKA_TESTER.getInjector();
    MetricsCollectionService collectionService = injector.getInstance(MetricsCollectionService.class);
    collectionService.startAndWait();
    LoggerContext loggerContext = new LocalAppenderContext(injector.getInstance(TransactionRunner.class),
                                                           injector.getInstance(LocationFactory.class),
                                                           injector.getInstance(MetricsCollectionService.class));
    final File logDir = TEMP_FOLDER.newFolder();
    loggerContext.putProperty("logDirectory", logDir.getAbsolutePath());

    LogPipelineConfigurator configurator = new LogPipelineConfigurator(CConfiguration.create());
    configurator.setContext(loggerContext);
    URL configURL = getClass().getClassLoader().getResource("pipeline-metric-appender.xml");
    Assert.assertNotNull(configURL);
    configurator.doConfigure(configURL);

    String topic = "metricsPipeline";
    TestCheckpointManager checkpointManager = new TestCheckpointManager();
    KafkaPipelineConfig config = new KafkaPipelineConfig(topic, Collections.singleton(0), 1024L, 100L, 1048576, 200L);
    KAFKA_TESTER.createTopic(topic, 1);

    loggerContext.start();
    KafkaLogProcessorPipeline pipeline = new KafkaLogProcessorPipeline(
      new LogProcessorPipelineContext(CConfiguration.create(), "testMetricAppender",
                                      loggerContext, NO_OP_METRICS_CONTEXT, 0),
      checkpointManager,
      KAFKA_TESTER.getBrokerService(), config);

    pipeline.startAndWait();

    // Publish some log messages to Kafka
    long now = System.currentTimeMillis();
    WorkerLoggingContext loggingContext =
      new WorkerLoggingContext("default", "app1", "worker1", "run1", "instance1");
    publishLog(topic,
               ImmutableList.of(
                 LogPipelineTestUtil.createLoggingEvent("test.logger", Level.INFO, "0", now - 1000),
                 LogPipelineTestUtil.createLoggingEvent("test.logger", Level.INFO, "2", now - 700),
                 LogPipelineTestUtil.createLoggingEvent("test.logger", Level.INFO, "3", now - 500),
                 LogPipelineTestUtil.createLoggingEvent("test.logger", Level.INFO, "1", now - 900),
                 LogPipelineTestUtil.createLoggingEvent("test.logger", Level.DEBUG, "hidden", now - 600),
                 LogPipelineTestUtil.createLoggingEvent("test.logger", Level.INFO, "4", now - 100)),
               loggingContext
    );

    WorkflowProgramLoggingContext workflowProgramLoggingContext =
      new WorkflowProgramLoggingContext("default", "app1", "wflow1", "run1", ProgramType.MAPREDUCE, "mr1", "mrun1");

    publishLog(topic,
               ImmutableList.of(
                 LogPipelineTestUtil.createLoggingEvent("test.logger", Level.WARN, "0", now - 1000),
                 LogPipelineTestUtil.createLoggingEvent("test.logger", Level.WARN, "2", now - 700),
                 LogPipelineTestUtil.createLoggingEvent("test.logger", Level.TRACE, "3", now - 500)),
               workflowProgramLoggingContext
    );

    ServiceLoggingContext serviceLoggingContext =
      new ServiceLoggingContext(NamespaceId.SYSTEM.getNamespace(), Constants.Logging.COMPONENT_NAME,
                                Constants.Service.TRANSACTION);
    publishLog(topic,
               ImmutableList.of(
                 LogPipelineTestUtil.createLoggingEvent("test.logger", Level.ERROR, "0", now - 1000),
                 LogPipelineTestUtil.createLoggingEvent("test.logger", Level.ERROR, "2", now - 700),
                 LogPipelineTestUtil.createLoggingEvent("test.logger", Level.ERROR, "3", now - 500),
                 LogPipelineTestUtil.createLoggingEvent("test.logger", Level.INFO, "1", now - 900)),
               serviceLoggingContext
    );

    final MetricStore metricStore = injector.getInstance(MetricStore.class);
    try {
      verifyMetricsWithRetry(metricStore,
                             new MetricDataQuery(0, Integer.MAX_VALUE, Integer.MAX_VALUE,
                                                 "system.app.log.info",
                                                 AggregationFunction.SUM,
                                                 LoggingContextHelper.getMetricsTags(loggingContext),
                                                 new ArrayList<>()), 5L);

      verifyMetricsWithRetry(metricStore,
                             new MetricDataQuery(0, Integer.MAX_VALUE, Integer.MAX_VALUE,
                                                 "system.app.log.debug",
                                                 AggregationFunction.SUM,
                                                 LoggingContextHelper.getMetricsTags(loggingContext),
                                                 new ArrayList<>()), 1L);

      verifyMetricsWithRetry(metricStore,
                             new MetricDataQuery(0, Integer.MAX_VALUE, Integer.MAX_VALUE,
                                                 "system.app.log.warn",
                                                 AggregationFunction.SUM,
                                                 // mapreduce metrics context
                                                 ImmutableMap.of(Constants.Metrics.Tag.NAMESPACE, "default",
                                                                 Constants.Metrics.Tag.APP, "app1",
                                                                 Constants.Metrics.Tag.MAPREDUCE, "mr1",
                                                                 Constants.Metrics.Tag.RUN_ID, "mrun1"),
                                                 new ArrayList<>()), 2L);
      verifyMetricsWithRetry(metricStore,
                             new MetricDataQuery(0, Integer.MAX_VALUE, Integer.MAX_VALUE,
                                                 "system.app.log.trace",
                                                 AggregationFunction.SUM,
                                                 // workflow metrics context
                                                 ImmutableMap.of(Constants.Metrics.Tag.NAMESPACE, "default",
                                                                 Constants.Metrics.Tag.APP, "app1",
                                                                 Constants.Metrics.Tag.WORKFLOW, "wflow1",
                                                                 Constants.Metrics.Tag.RUN_ID, "run1"),
                                                 new ArrayList<>()), 1L);
      verifyMetricsWithRetry(metricStore,
                             new MetricDataQuery(0, Integer.MAX_VALUE, Integer.MAX_VALUE,
                                                 "system.services.log.error",
                                                 AggregationFunction.SUM,
                                                 LoggingContextHelper.getMetricsTags(serviceLoggingContext),
                                                 new ArrayList<>()), 3L);
    } finally {
      pipeline.stopAndWait();
      loggerContext.stop();
      collectionService.stopAndWait();
    }
  }

}