class DummyClass_89314 {
  @Test
  public void testGetSystemStreamMetaDataWithRetry() {
    final List<PartitionInfo> partitionInfosForTopic = ImmutableList.of(mockPartitionInfo0, mockPartitionInfo1);
    when(mockKafkaConsumer.partitionsFor(VALID_TOPIC)).thenThrow(new RuntimeException())
        .thenReturn(partitionInfosForTopic);

    Map<String, SystemStreamMetadata> metadataMap =
        kafkaSystemAdmin.getSystemStreamMetadata(ImmutableSet.of(VALID_TOPIC));
    assertEquals("metadata should return for 1 topic", metadataMap.size(), 1);

    // retried twice because the first fails and the second succeeds
    Mockito.verify(mockKafkaConsumer, Mockito.times(2)).partitionsFor(VALID_TOPIC);

    final List<TopicPartition> topicPartitions =
        Arrays.asList(new TopicPartition(mockPartitionInfo0.topic(), mockPartitionInfo0.partition()),
            new TopicPartition(mockPartitionInfo1.topic(), mockPartitionInfo1.partition()));
    // the following methods thereafter are only called once
    Mockito.verify(mockKafkaConsumer, Mockito.times(1)).beginningOffsets(topicPartitions);
    Mockito.verify(mockKafkaConsumer, Mockito.times(1)).endOffsets(topicPartitions);
  }

}