class DummyClass_89311 {
  @Test
  public void testGetSSPMetadataEmptyPartition() {
    SystemStreamPartition ssp = new SystemStreamPartition(TEST_SYSTEM, VALID_TOPIC, new Partition(0));
    SystemStreamPartition otherSSP = new SystemStreamPartition(TEST_SYSTEM, "otherTopic", new Partition(1));
    TopicPartition topicPartition = new TopicPartition(VALID_TOPIC, 0);
    TopicPartition otherTopicPartition = new TopicPartition("otherTopic", 1);
    when(mockKafkaConsumer.beginningOffsets(ImmutableList.of(topicPartition, otherTopicPartition))).thenReturn(
        ImmutableMap.of(topicPartition, 1L));
    when(mockKafkaConsumer.endOffsets(ImmutableList.of(topicPartition, otherTopicPartition))).thenReturn(
        ImmutableMap.of(topicPartition, 11L));

    Map<SystemStreamPartition, SystemStreamMetadata.SystemStreamPartitionMetadata> expected =
        ImmutableMap.of(ssp, new SystemStreamMetadata.SystemStreamPartitionMetadata("1", "10", "11"), otherSSP,
            new SystemStreamMetadata.SystemStreamPartitionMetadata(null, null, null));
    assertEquals(expected, kafkaSystemAdmin.getSSPMetadata(ImmutableSet.of(ssp, otherSSP)));
  }

}