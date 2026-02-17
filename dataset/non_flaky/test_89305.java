class DummyClass_89305 {
  @Test
  public void testKafkaSystemConsumerMetrics() {
    String systemName = "system";
    TopicPartition tp1 = new TopicPartition("topic1", 1);
    TopicPartition tp2 = new TopicPartition("topic2", 2);
    String clientName = "clientName";

    // record expected values for further comparison
    Map<String, String> expectedValues = new HashMap<>();

    ReadableMetricsRegistry registry = new MetricsRegistryMap();
    KafkaSystemConsumerMetrics metrics = new KafkaSystemConsumerMetrics(systemName, registry);

    // initialize the metrics for the partitions
    metrics.registerTopicPartition(tp1);
    metrics.registerTopicPartition(tp2);

    // initialize the metrics for the host:port
    metrics.registerClientProxy(clientName);

    metrics.setOffsets(tp1, 1001);
    metrics.setOffsets(tp2, 1002);
    expectedValues.put(metrics.offsets().get(tp1).getName(), "1001");
    expectedValues.put(metrics.offsets().get(tp2).getName(), "1002");

    metrics.incBytesReads(tp1, 10);
    metrics.incBytesReads(tp1, 5); // total 15
    expectedValues.put(metrics.bytesRead().get(tp1).getName(), "15");

    metrics.incReads(tp1);
    metrics.incReads(tp1); // total 2
    expectedValues.put(metrics.reads().get(tp1).getName(), "2");

    metrics.setHighWatermarkValue(tp2, 1000);
    metrics.setHighWatermarkValue(tp2, 1001); // final value 1001
    expectedValues.put(metrics.highWatermark().get(tp2).getName(), "1001");

    metrics.setLagValue(tp1, 200);
    metrics.setLagValue(tp1, 201); // final value 201
    expectedValues.put(metrics.lag().get(tp1).getName(), "201");

    metrics.incClientBytesReads(clientName, 100); // broker-bytes-read
    metrics.incClientBytesReads(clientName, 110); // total 210
    expectedValues.put(metrics.clientBytesRead().get(clientName).getName(), "210");

    metrics.incClientReads(clientName); // messages-read
    metrics.incClientReads(clientName); // total 2
    expectedValues.put(metrics.clientReads().get(clientName).getName(), "2");

    metrics.setNumTopicPartitions(clientName, 2); // "topic-partitions"
    metrics.setNumTopicPartitions(clientName, 3); // final value 3
    expectedValues.put(metrics.topicPartitions().get(clientName).getName(), "3");


    String groupName = metrics.group();
    Assert.assertEquals(groupName, KafkaSystemConsumerMetrics.class.getName());
    Assert.assertEquals(metrics.systemName(), systemName);

    Map<String, Metric> metricMap = registry.getGroup(groupName);
    validate(metricMap, expectedValues);
  }

}