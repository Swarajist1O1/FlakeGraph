class DummyClass_104632 {
  @Test
  public void testUploadSameSegments()
      throws Exception {
    String offlineTableName = TableNameBuilder.OFFLINE.tableNameWithType(getTableName());
    SegmentZKMetadata segmentZKMetadata = _helixResourceManager.getSegmentsZKMetadata(offlineTableName).get(0);
    String segmentName = segmentZKMetadata.getSegmentName();
    long crc = segmentZKMetadata.getCrc();
    // Creation time is when the segment gets created
    long creationTime = segmentZKMetadata.getCreationTime();
    // Push time is when the segment gets first pushed (new segment)
    long pushTime = segmentZKMetadata.getPushTime();
    // Refresh time is when the segment gets refreshed (existing segment)
    long refreshTime = segmentZKMetadata.getRefreshTime();

    uploadSegments(offlineTableName, _tarDir);
    for (SegmentZKMetadata segmentZKMetadataAfterUpload : _helixResourceManager
        .getSegmentsZKMetadata(offlineTableName)) {
      // Only check one segment
      if (segmentZKMetadataAfterUpload.getSegmentName().equals(segmentName)) {
        assertEquals(segmentZKMetadataAfterUpload.getCrc(), crc);
        assertEquals(segmentZKMetadataAfterUpload.getCreationTime(), creationTime);
        assertEquals(segmentZKMetadataAfterUpload.getPushTime(), pushTime);
        // Refresh time should change
        assertTrue(segmentZKMetadataAfterUpload.getRefreshTime() > refreshTime);
        return;
      }
    }
  }

}