class DummyClass_89309 {
  @Test
  public void testGetSystemStreamMetaDataForTopicWithNoMessage() {
    // The topic with no messages will have beginningOffset = 0 and endOffset = 0
    when(mockKafkaConsumer.beginningOffsets(ImmutableList.of(testTopicPartition0, testTopicPartition1))).thenReturn(
        ImmutableMap.of(testTopicPartition0, 0L, testTopicPartition1, 0L));
    when(mockKafkaConsumer.endOffsets(ImmutableList.of(testTopicPartition0, testTopicPartition1))).thenReturn(
        ImmutableMap.of(testTopicPartition0, 0L, testTopicPartition1, 0L));

    Map<String, SystemStreamMetadata> metadataMap =
        kafkaSystemAdmin.getSystemStreamMetadata(ImmutableSet.of(VALID_TOPIC));
    assertEquals("metadata should return for 1 topic", metadataMap.size(), 1);

    // verify the metadata streamName
    assertEquals("the stream name should be " + VALID_TOPIC, metadataMap.get(VALID_TOPIC).getStreamName(), VALID_TOPIC);

    // verify the offset for each partition
    Map<Partition, SystemStreamMetadata.SystemStreamPartitionMetadata> systemStreamPartitionMetadata =
        metadataMap.get(VALID_TOPIC).getSystemStreamPartitionMetadata();
    assertEquals("there are 2 partitions", systemStreamPartitionMetadata.size(), 2);

    SystemStreamMetadata.SystemStreamPartitionMetadata partition0Metadata =
        systemStreamPartitionMetadata.get(new Partition(0));
    assertEquals("oldest offset for partition 0", partition0Metadata.getOldestOffset(), "0");
    assertEquals("upcoming offset for partition 0", partition0Metadata.getUpcomingOffset(), "0");
    assertEquals("newest offset is not set due to abnormal upcoming offset", partition0Metadata.getNewestOffset(),
        null);

    SystemStreamMetadata.SystemStreamPartitionMetadata partition1Metadata =
        systemStreamPartitionMetadata.get(new Partition(1));
    assertEquals("oldest offset for partition 1", partition1Metadata.getOldestOffset(), "0");
    assertEquals("upcoming offset for partition 1", partition1Metadata.getUpcomingOffset(), "0");
    assertEquals("newest offset is not set due to abnormal upcoming offset", partition1Metadata.getNewestOffset(),
        null);
  }

}