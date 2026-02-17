class DummyClass_104634 {
  @Test(dependsOnMethods = "testRangeIndexTriggering")
  public void testInvertedIndexTriggering()
      throws Exception {
    long numTotalDocs = getCountStarResult();

    // Without index on DivActualElapsedTime, all docs are scanned at filtering stage.
    assertEquals(postQuery(TEST_UPDATED_INVERTED_INDEX_QUERY).get("numEntriesScannedInFilter").asLong(), numTotalDocs);

    addInvertedIndex();
    long tableSizeWithNewIndex = getTableSize(getTableName());

    // Update table config to remove the new inverted index, and
    // reload table to clean the new inverted indices physically.
    TableConfig tableConfig = getOfflineTableConfig();
    tableConfig.getIndexingConfig().setInvertedIndexColumns(getInvertedIndexColumns());
    updateTableConfig(tableConfig);
    reloadOfflineTable(getTableName());
    TestUtils.waitForCondition(aVoid -> {
      try {
        JsonNode queryResponse = postQuery(TEST_UPDATED_INVERTED_INDEX_QUERY);
        // Total docs should not change during reload, but num entries scanned
        // gets back to total number of documents as the index is removed.
        assertEquals(queryResponse.get("totalDocs").asLong(), numTotalDocs);
        return queryResponse.get("numEntriesScannedInFilter").asLong() == numTotalDocs;
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }, 600_000L, "Failed to cleanup obsolete index");
    assertEquals(getTableSize(getTableName()), _tableSizeAfterRemovingIndex);

    // Add the inverted index back to test index removal via force download.
    addInvertedIndex();
    long tableSizeAfterAddingIndexAgain = getTableSize(getTableName());
    assertEquals(tableSizeAfterAddingIndexAgain, tableSizeWithNewIndex);

    // Update table config to remove the new inverted index.
    tableConfig = getOfflineTableConfig();
    tableConfig.getIndexingConfig().setInvertedIndexColumns(getInvertedIndexColumns());
    updateTableConfig(tableConfig);

    // Force to download a single segment, and disk usage should drop a bit.
    SegmentZKMetadata segmentZKMetadata =
        _helixResourceManager.getSegmentsZKMetadata(TableNameBuilder.OFFLINE.tableNameWithType(getTableName())).get(0);
    String segmentName = segmentZKMetadata.getSegmentName();
    reloadOfflineSegment(getTableName(), segmentName, true);
    TestUtils.waitForCondition(aVoid -> {
      try {
        JsonNode queryResponse = postQuery(TEST_UPDATED_INVERTED_INDEX_QUERY);
        // Total docs should not change during reload
        assertEquals(queryResponse.get("totalDocs").asLong(), numTotalDocs);
        return getTableSize(getTableName()) < tableSizeAfterAddingIndexAgain;
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }, 600_000L, "Failed to clean up obsolete index in segment");

    // Force to download the whole table and expect disk usage drops further.
    reloadOfflineTable(getTableName(), true);
    TestUtils.waitForCondition(aVoid -> {
      try {
        JsonNode queryResponse = postQuery(TEST_UPDATED_INVERTED_INDEX_QUERY);
        // Total docs should not change during reload, but num entries scanned
        // gets back to total number of documents as the index is removed.
        assertEquals(queryResponse.get("totalDocs").asLong(), numTotalDocs);
        return queryResponse.get("numEntriesScannedInFilter").asLong() == numTotalDocs;
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }, 600_000L, "Failed to cleanup obsolete index in table");
    // With force download, the table size gets back to the initial value.
    assertEquals(getTableSize(getTableName()), DISK_SIZE_IN_BYTES);
  }

}