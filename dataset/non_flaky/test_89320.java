class DummyClass_89320 {
  @Test
  public void testFetchThresholdShouldDivideEvenlyAmongPartitions() {
    final KafkaSystemConsumer consumer = createConsumer(FETCH_THRESHOLD_MSGS, FETCH_THRESHOLD_BYTES);
    final int partitionsNum = 50;
    for (int i = 0; i < partitionsNum; i++) {
      consumer.register(new SystemStreamPartition(TEST_SYSTEM, TEST_STREAM, new Partition(i)), "0");
    }

    consumer.start();

    Assert.assertEquals(Long.valueOf(FETCH_THRESHOLD_MSGS) / partitionsNum, consumer.perPartitionFetchThreshold);
    Assert.assertEquals(Long.valueOf(FETCH_THRESHOLD_BYTES) / 2 / partitionsNum,
        consumer.perPartitionFetchThresholdBytes);

    consumer.stop();
  }

}