class DummyClass_89324 {
  @Test
  public void testStartConsumer() {
    final Consumer consumer = Mockito.mock(Consumer.class);
    final KafkaConsumerProxyFactory kafkaConsumerProxyFactory = Mockito.mock(KafkaConsumerProxyFactory.class);

    final KafkaSystemConsumerMetrics kafkaSystemConsumerMetrics = new KafkaSystemConsumerMetrics(TEST_SYSTEM, new NoOpMetricsRegistry());
    final SystemStreamPartition testSystemStreamPartition1 = new SystemStreamPartition(TEST_SYSTEM, TEST_STREAM, new Partition(0));
    final SystemStreamPartition testSystemStreamPartition2 = new SystemStreamPartition(TEST_SYSTEM, TEST_STREAM, new Partition(1));
    final String testOffset = "1";
    final KafkaConsumerProxy kafkaConsumerProxy = Mockito.mock(KafkaConsumerProxy.class);

    Mockito.when(kafkaConsumerProxyFactory.create(Mockito.anyObject())).thenReturn(kafkaConsumerProxy);
    Mockito.doNothing().when(consumer).seek(new TopicPartition(TEST_STREAM, 0), 1);
    Mockito.doNothing().when(consumer).seek(new TopicPartition(TEST_STREAM, 1), 1);

    KafkaSystemConsumer kafkaSystemConsumer = new KafkaSystemConsumer(consumer, TEST_SYSTEM, new MapConfig(), TEST_CLIENT_ID, kafkaConsumerProxyFactory,
                                                                      kafkaSystemConsumerMetrics, new SystemClock());
    kafkaSystemConsumer.register(testSystemStreamPartition1, testOffset);
    kafkaSystemConsumer.register(testSystemStreamPartition2, testOffset);

    kafkaSystemConsumer.startConsumer();

    Mockito.verify(consumer).seek(new TopicPartition(TEST_STREAM, 0), 1);
    Mockito.verify(consumer).seek(new TopicPartition(TEST_STREAM, 1), 1);
    Mockito.verify(kafkaConsumerProxy).start();
    Mockito.verify(kafkaConsumerProxy).addTopicPartition(testSystemStreamPartition1, Long.valueOf(testOffset));
    Mockito.verify(kafkaConsumerProxy).addTopicPartition(testSystemStreamPartition2, Long.valueOf(testOffset));
  }

}