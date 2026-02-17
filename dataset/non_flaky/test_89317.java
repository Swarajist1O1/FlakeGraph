class DummyClass_89317 {
  @Test
  public void testGetSSPMetadataWithRetry() {
    SystemStreamPartition oneSSP = new SystemStreamPartition(TEST_SYSTEM, VALID_TOPIC, new Partition(0));
    SystemStreamPartition otherSSP = new SystemStreamPartition(TEST_SYSTEM, "otherTopic", new Partition(1));
    ImmutableSet<SystemStreamPartition> ssps = ImmutableSet.of(oneSSP, otherSSP);
    List<TopicPartition> topicPartitions = ssps.stream()
        .map(ssp -> new TopicPartition(ssp.getStream(), ssp.getPartition().getPartitionId()))
        .collect(Collectors.toList());
    Map<TopicPartition, Long> testBeginningOffsets =
        ImmutableMap.of(testTopicPartition0, KAFKA_BEGINNING_OFFSET_FOR_PARTITION0, testTopicPartition1,
            KAFKA_BEGINNING_OFFSET_FOR_PARTITION1);

    when(mockKafkaConsumer.beginningOffsets(topicPartitions)).thenThrow(new RuntimeException())
        .thenReturn(testBeginningOffsets);
    Map<SystemStreamPartition, SystemStreamMetadata.SystemStreamPartitionMetadata> sspMetadata =
        kafkaSystemAdmin.getSSPMetadata(ssps, new ExponentialSleepStrategy(2,
            1, 1));

    assertEquals("metadata should return for 2 topics", sspMetadata.size(), 2);

    // retried twice because the first fails and the second succeeds
    Mockito.verify(mockKafkaConsumer, Mockito.times(2)).beginningOffsets(topicPartitions);
  }

}