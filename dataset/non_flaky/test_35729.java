class DummyClass_35729 {
  @Test
  public void test() throws Exception {
    MetricsAdminSubscriberService adminService = injector.getInstance(MetricsAdminSubscriberService.class);
    adminService.startAndWait();

    // publish a metrics
    MetricsContext metricsContext = metricsCollectionService.getContext(
      Collections.singletonMap(Constants.Metrics.Tag.NAMESPACE, NamespaceId.SYSTEM.getNamespace()));
    metricsContext.increment("test.increment", 10L);
    metricsContext.gauge("test.gauge", 20L);

    MetricsSystemClient systemClient = injector.getInstance(RemoteMetricsSystemClient.class);

    // Search for metrics names
    Tasks.waitFor(true, () -> {
      Set<String> names = new HashSet<>(systemClient.search(metricsContext.getTags()));
      return names.contains("system.test.increment") && names.contains("system.test.gauge");
    }, 10, TimeUnit.SECONDS, 1, TimeUnit.SECONDS);

    // Query for metrics values
    Tasks.waitFor(true, () -> {
      Collection<MetricTimeSeries> values = systemClient.query(metricsContext.getTags(),
                                                               Arrays.asList("system.test.increment",
                                                                             "system.test.gauge"));
      // Find and match the values for the increment and gauge
      boolean incMatched = values.stream()
        .filter(timeSeries -> timeSeries.getMetricName().equals("system.test.increment"))
        .flatMap(timeSeries -> timeSeries.getTimeValues().stream())
        .findFirst()
        .filter(timeValue -> timeValue.getValue() == 10L)
        .isPresent();

      boolean gaugeMatched = values.stream()
        .filter(timeSeries -> timeSeries.getMetricName().equals("system.test.gauge"))
        .flatMap(timeSeries -> timeSeries.getTimeValues().stream())
        .findFirst()
        .filter(timeValue -> timeValue.getValue() == 20L)
        .isPresent();

      return incMatched && gaugeMatched;
    }, 10, TimeUnit.SECONDS, 1, TimeUnit.SECONDS);

    // Emit more metrics
    metricsContext.increment("test.increment", 40L);
    metricsContext.gauge("test.gauge", 40L);

    // Query for metrics values. Should see the latest aggregates
    Tasks.waitFor(true, () -> {
      Collection<MetricTimeSeries> values = systemClient.query(metricsContext.getTags(),
                                                               Arrays.asList("system.test.increment",
                                                                             "system.test.gauge"));
      // Find and match the values for the increment and gauge
      boolean incMatched = values.stream()
        .filter(timeSeries -> timeSeries.getMetricName().equals("system.test.increment"))
        .flatMap(timeSeries -> timeSeries.getTimeValues().stream())
        .findFirst()
        .filter(timeValue -> timeValue.getValue() == 50L)
        .isPresent();

      boolean gaugeMatched = values.stream()
        .filter(timeSeries -> timeSeries.getMetricName().equals("system.test.gauge"))
        .flatMap(timeSeries -> timeSeries.getTimeValues().stream())
        .findFirst()
        .filter(timeValue -> timeValue.getValue() == 40L)
        .isPresent();

      return incMatched && gaugeMatched;
    }, 10, TimeUnit.SECONDS, 1, TimeUnit.SECONDS);


    // Delete the increment metrics
    systemClient.delete(new MetricDeleteQuery(0, Integer.MAX_VALUE,
                                              Collections.emptySet(),
                                              metricsContext.getTags(),
                                              new ArrayList<>(metricsContext.getTags().keySet())));

    Tasks.waitFor(true, () -> {
      Collection<MetricTimeSeries> values = systemClient.query(metricsContext.getTags(),
                                                               Arrays.asList("system.test.increment",
                                                                             "system.test.gauge"));
      // increment should be missing
      boolean foundInc = values.stream()
        .anyMatch(timeSeries -> timeSeries.getMetricName().equals("system.test.increment"));

      // Find and match the values for gauge
      boolean foundGauge = values.stream()
        .anyMatch(timeSeries -> timeSeries.getMetricName().equals("system.test.gauge"));

      return !foundInc && !foundGauge;
    }, 1000, TimeUnit.SECONDS, 1, TimeUnit.SECONDS);

    adminService.stopAndWait();
  }

}