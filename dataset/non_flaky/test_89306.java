class DummyClass_89306 {
  @Test
  public void testGetSystemStreamMetaDataWithValidTopic() {
    System.out.println("STARTING");
    Map<String, SystemStreamMetadata> metadataMap =
        kafkaSystemAdmin.getSystemStreamMetadata(ImmutableSet.of(VALID_TOPIC));

    // verify metadata size
    assertEquals("metadata should return for 1 topic", metadataMap.size(), 1);
    System.out.println("STARTING1");
    // verify the metadata streamName
    assertEquals("the stream name should be " + VALID_TOPIC, metadataMap.get(VALID_TOPIC).getStreamName(), VALID_TOPIC);
    System.out.println("STARTING2");
    // verify the offset for each partition
    Map<Partition, SystemStreamMetadata.SystemStreamPartitionMetadata> systemStreamPartitionMetadata =
        metadataMap.get(VALID_TOPIC).getSystemStreamPartitionMetadata();
    assertEquals("there are 2 partitions", systemStreamPartitionMetadata.size(), 2);
    System.out.println("STARTING3");
    SystemStreamMetadata.SystemStreamPartitionMetadata partition0Metadata =
        systemStreamPartitionMetadata.get(new Partition(0));
    assertEquals("oldest offset for partition 0", partition0Metadata.getOldestOffset(),
        KAFKA_BEGINNING_OFFSET_FOR_PARTITION0.toString());
    assertEquals("upcoming offset for partition 0", partition0Metadata.getUpcomingOffset(),
        KAFKA_END_OFFSET_FOR_PARTITION0.toString());
    assertEquals("newest offset for partition 0", partition0Metadata.getNewestOffset(),
        Long.toString(KAFKA_END_OFFSET_FOR_PARTITION0 - 1));
    System.out.println("STARTING4");
    SystemStreamMetadata.SystemStreamPartitionMetadata partition1Metadata =
        systemStreamPartitionMetadata.get(new Partition(1));
    assertEquals("oldest offset for partition 1", partition1Metadata.getOldestOffset(),
        KAFKA_BEGINNING_OFFSET_FOR_PARTITION1.toString());
    assertEquals("upcoming offset for partition 1", partition1Metadata.getUpcomingOffset(),
        KAFKA_END_OFFSET_FOR_PARTITION1.toString());
    assertEquals("newest offset for partition 1", partition1Metadata.getNewestOffset(),
        Long.toString(KAFKA_END_OFFSET_FOR_PARTITION1 - 1));
  }

}