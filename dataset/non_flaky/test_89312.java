class DummyClass_89312 {
  @Test
  public void testGetSSPMetadataEmptyUpcomingOffset() {
    SystemStreamPartition ssp = new SystemStreamPartition(TEST_SYSTEM, VALID_TOPIC, new Partition(0));
    TopicPartition topicPartition = new TopicPartition(VALID_TOPIC, 0);
    when(mockKafkaConsumer.beginningOffsets(ImmutableList.of(topicPartition))).thenReturn(
        ImmutableMap.of(topicPartition, 0L));
    when(mockKafkaConsumer.endOffsets(ImmutableList.of(topicPartition))).thenReturn(ImmutableMap.of());
    Map<SystemStreamPartition, SystemStreamMetadata.SystemStreamPartitionMetadata> expected =
        ImmutableMap.of(ssp, new SystemStreamMetadata.SystemStreamPartitionMetadata("0", null, null));
    assertEquals(kafkaSystemAdmin.getSSPMetadata(ImmutableSet.of(ssp)), expected);
  }

}