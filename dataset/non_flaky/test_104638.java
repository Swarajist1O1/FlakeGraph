class DummyClass_104638 {
  @Test(dependsOnMethods = "testDefaultColumns")
  public void testBloomFilterTriggering()
      throws Exception {
    long numTotalDocs = getCountStarResult();
    assertEquals(postQuery(TEST_UPDATED_BLOOM_FILTER_QUERY).get("numSegmentsProcessed").asLong(), NUM_SEGMENTS);

    // Update table config and trigger reload
    TableConfig tableConfig = getOfflineTableConfig();
    tableConfig.getIndexingConfig().setBloomFilterColumns(UPDATED_BLOOM_FILTER_COLUMNS);
    updateTableConfig(tableConfig);
    reloadOfflineTable(getTableName());
    TestUtils.waitForCondition(aVoid -> {
      try {
        JsonNode queryResponse = postQuery(TEST_UPDATED_BLOOM_FILTER_QUERY);
        // Total docs should not change during reload
        assertEquals(queryResponse.get("totalDocs").asLong(), numTotalDocs);
        return queryResponse.get("numSegmentsProcessed").asLong() == 0L;
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }, 600_000L, "Failed to generate bloom filter");

    // Update table config to remove the new bloom filter, and
    // reload table to clean the new bloom filter physically.
    tableConfig = getOfflineTableConfig();
    tableConfig.getIndexingConfig().setBloomFilterColumns(getBloomFilterColumns());
    updateTableConfig(tableConfig);
    reloadOfflineTable(getTableName());
    TestUtils.waitForCondition(aVoid -> {
      try {
        JsonNode queryResponse = postQuery(TEST_UPDATED_BLOOM_FILTER_QUERY);
        // Total docs should not change during reload, but num entries scanned
        // gets back to total number of documents as bloom filter is removed.
        assertEquals(queryResponse.get("totalDocs").asLong(), numTotalDocs);
        return queryResponse.get("numSegmentsProcessed").asLong() == NUM_SEGMENTS;
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }, 600_000L, "Failed to cleanup obsolete index");
    assertEquals(getTableSize(getTableName()), _tableSizeAfterRemovingIndex);
  }

}