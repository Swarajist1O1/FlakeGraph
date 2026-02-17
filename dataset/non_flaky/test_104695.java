class DummyClass_104695 {
  @Test
  public void testSegmentFlushSize() {
    String realtimeTableName = TableNameBuilder.REALTIME.tableNameWithType(getTableName());
    List<SegmentZKMetadata> segmentsZKMetadata =
        ZKMetadataProvider.getSegmentsZKMetadata(_propertyStore, realtimeTableName);
    for (SegmentZKMetadata segmentZKMetadata : segmentsZKMetadata) {
      assertEquals(segmentZKMetadata.getSizeThresholdToFlushSegment(),
          getRealtimeSegmentFlushSize() / getNumKafkaPartitions());
    }
  }

}