class DummyClass_89313 {
  @Test
  public void testGetSSPMetadataZeroUpcomingOffset() {
    SystemStreamPartition ssp = new SystemStreamPartition(TEST_SYSTEM, VALID_TOPIC, new Partition(0));
    TopicPartition topicPartition = new TopicPartition(VALID_TOPIC, 0);
    when(mockKafkaConsumer.beginningOffsets(ImmutableList.of(topicPartition))).thenReturn(
        ImmutableMap.of(topicPartition, -1L));
    when(mockKafkaConsumer.endOffsets(ImmutableList.of(topicPartition))).thenReturn(
        ImmutableMap.of(topicPartition, 0L));
    Map<SystemStreamPartition, SystemStreamMetadata.SystemStreamPartitionMetadata> expected =
        ImmutableMap.of(ssp, new SystemStreamMetadata.SystemStreamPartitionMetadata("0", null, "0"));
    assertEquals(kafkaSystemAdmin.getSSPMetadata(ImmutableSet.of(ssp)), expected);
  }

}