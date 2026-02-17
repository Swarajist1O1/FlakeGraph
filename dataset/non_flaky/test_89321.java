class DummyClass_89321 {
  @Test
  public void testConsumerRegisterOlderOffsetOfTheSamzaSSP() {

    KafkaSystemConsumer consumer = createConsumer(FETCH_THRESHOLD_MSGS, FETCH_THRESHOLD_BYTES);

    SystemStreamPartition ssp0 = new SystemStreamPartition(TEST_SYSTEM, TEST_STREAM, new Partition(0));
    SystemStreamPartition ssp1 = new SystemStreamPartition(TEST_SYSTEM, TEST_STREAM, new Partition(1));
    SystemStreamPartition ssp2 = new SystemStreamPartition(TEST_SYSTEM, TEST_STREAM, new Partition(2));

    consumer.register(ssp0, "0");
    consumer.register(ssp0, "5");
    consumer.register(ssp1, "2");
    consumer.register(ssp1, "3");
    consumer.register(ssp2, "0");

    assertEquals("0", consumer.topicPartitionsToOffset.get(KafkaSystemConsumer.toTopicPartition(ssp0)));
    assertEquals("2", consumer.topicPartitionsToOffset.get(KafkaSystemConsumer.toTopicPartition(ssp1)));
    assertEquals("0", consumer.topicPartitionsToOffset.get(KafkaSystemConsumer.toTopicPartition(ssp2)));
  }

}