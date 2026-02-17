class DummyClass_104637 {
  @Test(dependsOnMethods = "testBloomFilterTriggering")
  public void testRangeIndexTriggering()
      throws Exception {
    long numTotalDocs = getCountStarResult();
    assertEquals(postQuery(TEST_UPDATED_RANGE_INDEX_QUERY).get("numEntriesScannedInFilter").asLong(), numTotalDocs);

    // Update table config and trigger reload
    TableConfig tableConfig = getOfflineTableConfig();
    tableConfig.getIndexingConfig().setRangeIndexColumns(UPDATED_RANGE_INDEX_COLUMNS);
    updateTableConfig(tableConfig);
    reloadOfflineTable(getTableName());
    TestUtils.waitForCondition(aVoid -> {
      try {
        JsonNode queryResponse = postQuery(TEST_UPDATED_RANGE_INDEX_QUERY);
        // Total docs should not change during reload
        assertEquals(queryResponse.get("totalDocs").asLong(), numTotalDocs);
        return queryResponse.get("numEntriesScannedInFilter").asLong() < numTotalDocs;
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }, 600_000L, "Failed to generate range index");

    // Update table config to remove the new range index, and
    // reload table to clean the new range index physically.
    tableConfig = getOfflineTableConfig();
    tableConfig.getIndexingConfig().setRangeIndexColumns(getRangeIndexColumns());
    updateTableConfig(tableConfig);
    reloadOfflineTable(getTableName());
    TestUtils.waitForCondition(aVoid -> {
      try {
        JsonNode queryResponse = postQuery(TEST_UPDATED_RANGE_INDEX_QUERY);
        // Total docs should not change during reload, but num entries scanned
        // gets back to total number of documents as the index is removed.
        assertEquals(queryResponse.get("totalDocs").asLong(), numTotalDocs);
        return queryResponse.get("numEntriesScannedInFilter").asLong() == numTotalDocs;
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }, 600_000L, "Failed to cleanup obsolete index");

    assertEquals(getTableSize(getTableName()), _tableSizeAfterRemovingIndex);
  }

}