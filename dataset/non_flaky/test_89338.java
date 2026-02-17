class DummyClass_89338 {
  @Test
  public void testShouldAssembleMetadata() {
    Map<SystemStreamPartition, String> oldestOffsets = new ImmutableMap.Builder<SystemStreamPartition, String>()
        .put(new SystemStreamPartition(SYSTEM, "stream1", new Partition(0)), "o1")
        .put(new SystemStreamPartition(SYSTEM, "stream2", new Partition(0)), "o2")
        .put(new SystemStreamPartition(SYSTEM, "stream1", new Partition(1)), "o3")
        .put(new SystemStreamPartition(SYSTEM, "stream2", new Partition(1)), "o4")
        .build();

    Map<SystemStreamPartition, String> newestOffsets = new ImmutableMap.Builder<SystemStreamPartition, String>()
        .put(new SystemStreamPartition(SYSTEM, "stream1", new Partition(0)), "n1")
        .put(new SystemStreamPartition(SYSTEM, "stream2", new Partition(0)), "n2")
        .put(new SystemStreamPartition(SYSTEM, "stream1", new Partition(1)), "n3")
        .put(new SystemStreamPartition(SYSTEM, "stream2", new Partition(1)), "n4")
        .build();

    Map<SystemStreamPartition, String> upcomingOffsets = new ImmutableMap.Builder<SystemStreamPartition, String>()
        .put(new SystemStreamPartition(SYSTEM, "stream1", new Partition(0)), "u1")
        .put(new SystemStreamPartition(SYSTEM, "stream2", new Partition(0)), "u2")
        .put(new SystemStreamPartition(SYSTEM, "stream1", new Partition(1)), "u3")
        .put(new SystemStreamPartition(SYSTEM, "stream2", new Partition(1)), "u4")
        .build();

    Map<String, SystemStreamMetadata> metadata = assembleMetadata(oldestOffsets, newestOffsets, upcomingOffsets);
    assertNotNull(metadata);
    assertEquals(2, metadata.size());
    assertTrue(metadata.containsKey("stream1"));
    assertTrue(metadata.containsKey("stream2"));
    SystemStreamMetadata stream1Metadata = metadata.get("stream1");
    SystemStreamMetadata stream2Metadata = metadata.get("stream2");
    assertNotNull(stream1Metadata);
    assertNotNull(stream2Metadata);
    assertEquals("stream1", stream1Metadata.getStreamName());
    assertEquals("stream2", stream2Metadata.getStreamName());
    SystemStreamMetadata.SystemStreamPartitionMetadata expectedSystemStream1Partition0Metadata =
        new SystemStreamMetadata.SystemStreamPartitionMetadata("o1", "n1", "u1");
    SystemStreamMetadata.SystemStreamPartitionMetadata expectedSystemStream1Partition1Metadata =
        new SystemStreamMetadata.SystemStreamPartitionMetadata("o3", "n3", "u3");
    SystemStreamMetadata.SystemStreamPartitionMetadata expectedSystemStream2Partition0Metadata =
        new SystemStreamMetadata.SystemStreamPartitionMetadata("o2", "n2", "u2");
    SystemStreamMetadata.SystemStreamPartitionMetadata expectedSystemStream2Partition1Metadata =
        new SystemStreamMetadata.SystemStreamPartitionMetadata("o4", "n4", "u4");
    Map<Partition, SystemStreamMetadata.SystemStreamPartitionMetadata> stream1PartitionMetadata =
        stream1Metadata.getSystemStreamPartitionMetadata();
    Map<Partition, SystemStreamMetadata.SystemStreamPartitionMetadata> stream2PartitionMetadata =
        stream2Metadata.getSystemStreamPartitionMetadata();
    assertEquals(expectedSystemStream1Partition0Metadata, stream1PartitionMetadata.get(new Partition(0)));
    assertEquals(expectedSystemStream1Partition1Metadata, stream1PartitionMetadata.get(new Partition(1)));
    assertEquals(expectedSystemStream2Partition0Metadata, stream2PartitionMetadata.get(new Partition(0)));
    assertEquals(expectedSystemStream2Partition1Metadata, stream2PartitionMetadata.get(new Partition(1)));
  }

}