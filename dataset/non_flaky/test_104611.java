class DummyClass_104611 {
  @Test
  public void testPartitionMetadata() {
    int[] numSegmentsForPartition = new int[2];
    String realtimeTableName = TableNameBuilder.REALTIME.tableNameWithType(getTableName());
    List<SegmentZKMetadata> segmentsZKMetadata = _helixResourceManager.getSegmentsZKMetadata(realtimeTableName);
    for (SegmentZKMetadata segmentZKMetadata : segmentsZKMetadata) {
      SegmentPartitionMetadata segmentPartitionMetadata = segmentZKMetadata.getPartitionMetadata();
      assertNotNull(segmentPartitionMetadata);
      Map<String, ColumnPartitionMetadata> columnPartitionMetadataMap =
          segmentPartitionMetadata.getColumnPartitionMap();
      assertEquals(columnPartitionMetadataMap.size(), 1);
      ColumnPartitionMetadata columnPartitionMetadata = columnPartitionMetadataMap.get(PARTITION_COLUMN);
      assertNotNull(columnPartitionMetadata);
      assertTrue(columnPartitionMetadata.getFunctionName().equalsIgnoreCase("murmur"));
      assertEquals(columnPartitionMetadata.getNumPartitions(), 2);
      int partitionGroupId = new LLCSegmentName(segmentZKMetadata.getSegmentName()).getPartitionGroupId();
      assertEquals(columnPartitionMetadata.getPartitions(), Collections.singleton(partitionGroupId));
      numSegmentsForPartition[partitionGroupId]++;
    }

    // There should be 2 segments for partition 0, 2 segments for partition 1
    assertEquals(numSegmentsForPartition[0], 2);
    assertEquals(numSegmentsForPartition[1], 2);
  }

}