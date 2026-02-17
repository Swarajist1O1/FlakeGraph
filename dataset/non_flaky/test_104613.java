class DummyClass_104613 {
  @Test(dependsOnMethods = "testPartitionRouting")
  public void testNonPartitionedStream()
      throws Exception {
    // Push the second Avro file into Kafka without partitioning
    _partitionColumn = null;
    pushAvroIntoKafka(Collections.singletonList(_avroFiles.get(1)));

    // Wait for all documents loaded
    _countStarResult += NUM_DOCS_IN_SECOND_AVRO_FILE;
    waitForAllDocsLoaded(600_000L);

    // Check partition metadata
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
      numSegmentsForPartition[partitionGroupId]++;

      if (segmentZKMetadata.getStatus() == Status.IN_PROGRESS) {
        // For consuming segment, the partition metadata should only contain the stream partition
        assertEquals(columnPartitionMetadata.getPartitions(), Collections.singleton(partitionGroupId));
      } else {
        LLCSegmentName llcSegmentName = new LLCSegmentName(segmentZKMetadata.getSegmentName());
        int sequenceNumber = llcSegmentName.getSequenceNumber();
        if (sequenceNumber == 0) {
          // The partition metadata for the first completed segment should only contain the stream partition
          assertEquals(columnPartitionMetadata.getPartitions(), Collections.singleton(partitionGroupId));
        } else {
          // The partition metadata for the new completed segments should contain both partitions
          assertEquals(columnPartitionMetadata.getPartitions(), new HashSet<>(Arrays.asList(0, 1)));
        }
      }
    }

    // There should be 4 segments for partition 0, 4 segments for partition 1
    assertEquals(numSegmentsForPartition[0], 4);
    assertEquals(numSegmentsForPartition[1], 4);

    // Check partition routing
    int numSegments = segmentsZKMetadata.size();

    // Query partition 0
    {
      String query = "SELECT COUNT(*) FROM mytable WHERE DestState = 'CA'";
      JsonNode response = postQuery(query);

      String queryToCompare = "SELECT COUNT(*) FROM mytable WHERE DestState BETWEEN 'CA' AND 'CA'";
      JsonNode responseToCompare = postQuery(queryToCompare);

      // Should skip the first completed segments and the consuming segment for partition 1
      assertEquals(response.get(MetadataKey.NUM_SEGMENTS_QUERIED.getName()).asInt(), numSegments - 2);
      assertEquals(responseToCompare.get(MetadataKey.NUM_SEGMENTS_QUERIED.getName()).asInt(), numSegments);

      // The result won't match because the consuming segment for partition 1 is pruned out
    }

    // Query partition 1
    {
      String query = "SELECT COUNT(*) FROM mytable WHERE DestState = 'FL'";
      JsonNode response = postQuery(query);

      String queryToCompare = "SELECT COUNT(*) FROM mytable WHERE DestState BETWEEN 'FL' AND 'FL'";
      JsonNode responseToCompare = postQuery(queryToCompare);

      // Should skip the first completed segments and the consuming segment for partition 0
      assertEquals(response.get(MetadataKey.NUM_SEGMENTS_QUERIED.getName()).asInt(), numSegments - 2);
      assertEquals(responseToCompare.get(MetadataKey.NUM_SEGMENTS_QUERIED.getName()).asInt(), numSegments);

      // The result won't match because the consuming segment for partition 0 is pruned out
    }

    // Push the third Avro file into Kafka with partitioning
    _partitionColumn = PARTITION_COLUMN;
    pushAvroIntoKafka(Collections.singletonList(_avroFiles.get(2)));

    // Wait for all documents loaded
    _countStarResult += NUM_DOCS_IN_THIRD_AVRO_FILE;
    waitForAllDocsLoaded(600_000L);

    // Check partition metadata
    numSegmentsForPartition = new int[2];
    segmentsZKMetadata = _helixResourceManager.getSegmentsZKMetadata(realtimeTableName);
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
      numSegmentsForPartition[partitionGroupId]++;

      if (segmentZKMetadata.getStatus() == Status.IN_PROGRESS) {
        // For consuming segment, the partition metadata should only contain the stream partition
        assertEquals(columnPartitionMetadata.getPartitions(), Collections.singleton(partitionGroupId));
      } else {
        // The partition metadata for the new completed segments should only contain the stream partition
        LLCSegmentName llcSegmentName = new LLCSegmentName(segmentZKMetadata.getSegmentName());
        int sequenceNumber = llcSegmentName.getSequenceNumber();
        if (sequenceNumber == 0 || sequenceNumber >= 4) {
          // The partition metadata for the first and new completed segments should only contain the stream partition
          assertEquals(columnPartitionMetadata.getPartitions(), Collections.singleton(partitionGroupId));
        } else {
          // The partition metadata for the completed segments containing records from the second Avro file should
          // contain both partitions
          assertEquals(columnPartitionMetadata.getPartitions(), new HashSet<>(Arrays.asList(0, 1)));
        }
      }
    }

    // There should be 6 segments for partition 0, 6 segments for partition 1
    assertEquals(numSegmentsForPartition[0], 6);
    assertEquals(numSegmentsForPartition[1], 6);

    // Check partition routing
    numSegments = segmentsZKMetadata.size();

    // Query partition 0
    {
      String query = "SELECT COUNT(*) FROM mytable WHERE DestState = 'CA'";
      JsonNode response = postQuery(query);

      String queryToCompare = "SELECT COUNT(*) FROM mytable WHERE DestState BETWEEN 'CA' AND 'CA'";
      JsonNode responseToCompare = postQuery(queryToCompare);

      // Should skip 2 completed segments and the consuming segment for partition 1
      assertEquals(response.get(MetadataKey.NUM_SEGMENTS_QUERIED.getName()).asInt(), numSegments - 3);
      assertEquals(responseToCompare.get(MetadataKey.NUM_SEGMENTS_QUERIED.getName()).asInt(), numSegments);

      // The result should match again after all the segments with the non-partitioning records are committed
      assertEquals(response.get("aggregationResults").get(0).get("value").asInt(),
          responseToCompare.get("aggregationResults").get(0).get("value").asInt());
    }

    // Query partition 1
    {
      String query = "SELECT COUNT(*) FROM mytable WHERE DestState = 'FL'";
      JsonNode response = postQuery(query);

      String queryToCompare = "SELECT COUNT(*) FROM mytable WHERE DestState BETWEEN 'FL' AND 'FL'";
      JsonNode responseToCompare = postQuery(queryToCompare);

      // Should skip 2 completed segments and the consuming segment for partition 0
      assertEquals(response.get(MetadataKey.NUM_SEGMENTS_QUERIED.getName()).asInt(), numSegments - 3);
      assertEquals(responseToCompare.get(MetadataKey.NUM_SEGMENTS_QUERIED.getName()).asInt(), numSegments);

      // The result should match again after all the segments with the non-partitioning records are committed
      assertEquals(response.get("aggregationResults").get(0).get("value").asInt(),
          responseToCompare.get("aggregationResults").get(0).get("value").asInt());
    }
  }

}