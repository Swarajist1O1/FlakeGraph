class DummyClass_35734 {
  @Test
  public void testPublish() throws InterruptedException {
    final BlockingQueue<MetricValues> published = new LinkedBlockingQueue<>();

    AggregatedMetricsCollectionService service = new AggregatedMetricsCollectionService(1000L) {
      @Override
      protected void publish(Iterator<MetricValues> metrics) {
        Iterators.addAll(published, metrics);
      }
    };

    service.startAndWait();

    // non-empty tags.
    final Map<String, String> baseTags = ImmutableMap.of(Constants.Metrics.Tag.NAMESPACE, NAMESPACE,
                                                         Constants.Metrics.Tag.APP, APP,
                                                         Constants.Metrics.Tag.SERVICE, SERVICE,
                                                         Constants.Metrics.Tag.RUN_ID, RUNID);

    try {
      // The first section tests with empty tags.
      // Publish couple metrics with empty tags, they should be aggregated.
      service.getContext(EMPTY_TAGS).increment(METRIC, Integer.MAX_VALUE);
      service.getContext(EMPTY_TAGS).increment(METRIC, 2);
      service.getContext(EMPTY_TAGS).increment(METRIC, 3);
      service.getContext(EMPTY_TAGS).increment(METRIC, 4);

      verifyCounterMetricsValue(published, ImmutableMap.of(0, ImmutableMap.of(METRIC, 9L + Integer.MAX_VALUE)));

      // No publishing for 0 value metrics
      Assert.assertNull(published.poll(3, TimeUnit.SECONDS));

      //update the metrics multiple times with gauge.
      service.getContext(EMPTY_TAGS).gauge(GAUGE_METRIC, 1);
      service.getContext(EMPTY_TAGS).gauge(GAUGE_METRIC, 2);
      service.getContext(EMPTY_TAGS).gauge(GAUGE_METRIC, 3);

      // gauge just updates the value, so polling should return the most recent value written
      verifyGaugeMetricsValue(published, ImmutableMap.of(0, 3L));

      // define collectors for non-empty tags
      MetricsContext baseCollector = service.getContext(baseTags);
      MetricsContext metricsContext = baseCollector.childContext(Constants.Metrics.Tag.HANDLER, HANDLER)
        .childContext(Constants.Metrics.Tag.INSTANCE_ID, INSTANCE);

      // increment metrics for various collectors
      baseCollector.increment(METRIC, Integer.MAX_VALUE);
      metricsContext.increment(METRIC, 5);
      baseCollector.increment(METRIC, 10);
      baseCollector.increment(METRIC, 3);
      metricsContext.increment(METRIC, 2);
      metricsContext.increment(METRIC, 4);
      metricsContext.increment(METRIC, 3);
      metricsContext.increment(METRIC, 1);

      // there are two collectors, verify their metrics values
      verifyCounterMetricsValue(published, ImmutableMap.of(4, ImmutableMap.of(METRIC, 13L + Integer.MAX_VALUE),
                                                           6, ImmutableMap.of(METRIC, 15L)));

      // No publishing for 0 value metrics
      Assert.assertNull(published.poll(3, TimeUnit.SECONDS));

      // gauge metrics for various collectors
      baseCollector.gauge(GAUGE_METRIC, Integer.MAX_VALUE);
      baseCollector.gauge(GAUGE_METRIC, 3);
      metricsContext.gauge(GAUGE_METRIC, 6);
      metricsContext.gauge(GAUGE_METRIC, 2);
      baseCollector.gauge(GAUGE_METRIC, 1);
      metricsContext.gauge(GAUGE_METRIC, Integer.MAX_VALUE);

      // gauge just updates the value, so polling should return the most recent value written
      verifyGaugeMetricsValue(published, ImmutableMap.of(4, 1L, 6, (long) Integer.MAX_VALUE));

      metricsContext.gauge(GAUGE_METRIC, 0);
      verifyCounterMetricsValue(published, ImmutableMap.of(6, ImmutableMap.of(GAUGE_METRIC, 0L)));
    } finally {
      service.stopAndWait();
    }
  }

}