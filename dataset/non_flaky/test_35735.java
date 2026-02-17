class DummyClass_35735 {
  @Test
  public void testMessagingPublish() throws TopicNotFoundException {

    MetricsCollectionService collectionService = new MessagingMetricsCollectionService(CConfiguration.create(),
                                                                                       messagingService,
                                                                                       recordWriter);
    collectionService.startAndWait();

    // publish metrics for different context
    for (int i = 1; i <= 3; i++) {
      collectionService.getContext(ImmutableMap.of("tag", "" + i)).increment("processed", i);
    }

    collectionService.stopAndWait();

    // <Context, metricName, value>
    Table<String, String, Long> expected = HashBasedTable.create();
    expected.put("tag.1", "processed", 1L);
    expected.put("tag.2", "processed", 2L);
    expected.put("tag.3", "processed", 3L);

    ReflectionDatumReader<MetricValues> recordReader = new ReflectionDatumReader<>(schema, metricValueType);
    assertMetricsFromMessaging(schema, recordReader, expected);
  }

}