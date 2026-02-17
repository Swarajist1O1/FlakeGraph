class DummyClass_89318 {
  @Test(expected = SamzaException.class)
  public void testGetSSPMetadataShouldTerminateAfterFiniteRetriesOnException() throws Exception {
    SystemStreamPartition oneSSP = new SystemStreamPartition(TEST_SYSTEM, VALID_TOPIC, new Partition(0));
    SystemStreamPartition otherSSP = new SystemStreamPartition(TEST_SYSTEM, "otherTopic", new Partition(1));

    ImmutableSet<SystemStreamPartition> ssps = ImmutableSet.of(oneSSP, otherSSP);
    List<TopicPartition> topicPartitions = ssps.stream()
        .map(ssp -> new TopicPartition(ssp.getStream(), ssp.getPartition().getPartitionId()))
        .collect(Collectors.toList());

    when(mockKafkaConsumer.beginningOffsets(topicPartitions)).thenThrow(new RuntimeException())
        .thenThrow(new RuntimeException());

    kafkaSystemAdmin.getSSPMetadata(ssps, new ExponentialSleepStrategy(2,
        1, 1));
  }

}