class DummyClass_35731 {
  @Test
  public void testMetricsProcessor() throws Exception {
    injector.getInstance(TransactionManager.class).startAndWait();
    StructuredTableRegistry structuredTableRegistry = injector.getInstance(StructuredTableRegistry.class);
    structuredTableRegistry.initialize();
    StoreDefinition.createAllTables(injector.getInstance(StructuredTableAdmin.class), structuredTableRegistry);
    injector.getInstance(DatasetOpExecutorService.class).startAndWait();
    injector.getInstance(DatasetService.class).startAndWait();

    final MetricStore metricStore = injector.getInstance(MetricStore.class);

    Set<Integer> partitions = new HashSet<>();
    for (int i = 0; i < cConf.getInt(Constants.Metrics.MESSAGING_TOPIC_NUM); i++) {
      partitions.add(i);
    }

    // Start KafkaMetricsProcessorService after metrics are published to Kafka

    // Intentionally set queue size to a small value, so that MessagingMetricsProcessorManagerService
    // internally can persist metrics when more messages are to be fetched
    MessagingMetricsProcessorManagerService messagingMetricsProcessorManagerService =
      new MessagingMetricsProcessorManagerService(cConf, injector.getInstance(MetricDatasetFactory.class),
                                                  messagingService, injector.getInstance(SchemaGenerator.class),
                                                  injector.getInstance(DatumReaderFactory.class),
                                                  metricStore, injector.getInstance(MetricsWriterProvider.class),
                                                  partitions, new NoopMetricsContext(), 50, 0);
    messagingMetricsProcessorManagerService.startAndWait();

    long startTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
    // Publish metrics with messaging service and record expected metrics
    for (int i = 10; i < 20; i++) {
      publishMessagingMetrics(i, startTime, METRICS_CONTEXT, expected, SYSTEM_METRIC_PREFIX, MetricType.COUNTER);
    }

    Thread.sleep(500);
    // Stop and restart messagingMetricsProcessorManagerService
    messagingMetricsProcessorManagerService.stopAndWait();
    // Intentionally set queue size to a large value, so that MessagingMetricsProcessorManagerService
    // internally only persists metrics during terminating.
    messagingMetricsProcessorManagerService =
      new MessagingMetricsProcessorManagerService(cConf, injector.getInstance(MetricDatasetFactory.class),
                                                  messagingService, injector.getInstance(SchemaGenerator.class),
                                                  injector.getInstance(DatumReaderFactory.class),
                                                  metricStore, injector.getInstance(MetricsWriterProvider.class),
                                                  partitions, new NoopMetricsContext(), 50, 0);
    messagingMetricsProcessorManagerService.startAndWait();

    // Publish metrics after MessagingMetricsProcessorManagerService restarts and record expected metrics
    for (int i = 20; i < 30; i++) {
      publishMessagingMetrics(i, startTime, METRICS_CONTEXT, expected, SYSTEM_METRIC_PREFIX, MetricType.GAUGE);
    }

    final List<String> missingMetricNames = new ArrayList<>();
    // Wait until all expected metrics can be queried from the metric store. If not all expected metrics
    // are retrieved when timeout occurs, print out the missing metrics
    try {
      Tasks.waitFor(true, new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
          return canQueryAllMetrics(metricStore, METRICS_CONTEXT, expected, missingMetricNames);
        }

}