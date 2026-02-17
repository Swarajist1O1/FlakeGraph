class DummyClass_104640 {
  @Test
  public void testStarTreeTriggering()
      throws Exception {
    long numTotalDocs = getCountStarResult();
    long tableSizeWithDefaultIndex = getTableSize(getTableName());

    // Test the first query
    JsonNode firstQueryResponse = postQuery(TEST_STAR_TREE_QUERY_1);
    int firstQueryResult = firstQueryResponse.get("aggregationResults").get(0).get("value").asInt();
    assertEquals(firstQueryResponse.get("totalDocs").asLong(), numTotalDocs);
    // Initially 'numDocsScanned' should be the same as 'COUNT(*)' result
    assertEquals(firstQueryResponse.get("numDocsScanned").asInt(), firstQueryResult);

    // Update table config and trigger reload
    TableConfig tableConfig = getOfflineTableConfig();
    IndexingConfig indexingConfig = tableConfig.getIndexingConfig();
    indexingConfig.setStarTreeIndexConfigs(Collections.singletonList(STAR_TREE_INDEX_CONFIG_1));
    indexingConfig.setEnableDynamicStarTreeCreation(true);
    updateTableConfig(tableConfig);
    reloadOfflineTable(getTableName());

    TestUtils.waitForCondition(aVoid -> {
      try {
        JsonNode queryResponse = postQuery(TEST_STAR_TREE_QUERY_1);
        // Result should not change during reload
        assertEquals(queryResponse.get("aggregationResults").get(0).get("value").asInt(), firstQueryResult);
        // Total docs should not change during reload
        assertEquals(queryResponse.get("totalDocs").asLong(), numTotalDocs);
        // With star-tree, 'numDocsScanned' should be the same as number of segments (1 per segment)
        return queryResponse.get("numDocsScanned").asInt() == NUM_SEGMENTS;
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }, 600_000L, "Failed to add first star-tree index");

    // Reload again should have no effect
    reloadOfflineTable(getTableName());
    firstQueryResponse = postQuery(TEST_STAR_TREE_QUERY_1);
    assertEquals(firstQueryResponse.get("aggregationResults").get(0).get("value").asInt(), firstQueryResult);
    assertEquals(firstQueryResponse.get("totalDocs").asLong(), numTotalDocs);
    assertEquals(firstQueryResponse.get("numDocsScanned").asInt(), NUM_SEGMENTS);

    // Should be able to use the star-tree with an additional match-all predicate on another dimension
    firstQueryResponse = postQuery(TEST_STAR_TREE_QUERY_1 + " AND DaysSinceEpoch > 16070");
    assertEquals(firstQueryResponse.get("aggregationResults").get(0).get("value").asInt(), firstQueryResult);
    assertEquals(firstQueryResponse.get("totalDocs").asLong(), numTotalDocs);
    assertEquals(firstQueryResponse.get("numDocsScanned").asInt(), NUM_SEGMENTS);

    // Test the second query
    JsonNode secondQueryResponse = postQuery(TEST_STAR_TREE_QUERY_2);
    int secondQueryResult = secondQueryResponse.get("aggregationResults").get(0).get("value").asInt();
    assertEquals(secondQueryResponse.get("totalDocs").asLong(), numTotalDocs);
    // Initially 'numDocsScanned' should be the same as 'COUNT(*)' result
    assertEquals(secondQueryResponse.get("numDocsScanned").asInt(), secondQueryResult);

    // Update table config with a different star-tree index config and trigger reload
    indexingConfig.setStarTreeIndexConfigs(Collections.singletonList(STAR_TREE_INDEX_CONFIG_2));
    updateTableConfig(tableConfig);
    reloadOfflineTable(getTableName());

    TestUtils.waitForCondition(aVoid -> {
      try {
        JsonNode queryResponse = postQuery(TEST_STAR_TREE_QUERY_2);
        // Result should not change during reload
        assertEquals(queryResponse.get("aggregationResults").get(0).get("value").asInt(), secondQueryResult);
        // Total docs should not change during reload
        assertEquals(queryResponse.get("totalDocs").asLong(), numTotalDocs);
        // With star-tree, 'numDocsScanned' should be the same as number of segments (1 per segment)
        return queryResponse.get("numDocsScanned").asInt() == NUM_SEGMENTS;
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }, 600_000L, "Failed to change to second star-tree index");

    // First query should not be able to use the star-tree
    firstQueryResponse = postQuery(TEST_STAR_TREE_QUERY_1);
    assertEquals(firstQueryResponse.get("numDocsScanned").asInt(), firstQueryResult);

    // Reload again should have no effect
    reloadOfflineTable(getTableName());
    firstQueryResponse = postQuery(TEST_STAR_TREE_QUERY_1);
    assertEquals(firstQueryResponse.get("aggregationResults").get(0).get("value").asInt(), firstQueryResult);
    assertEquals(firstQueryResponse.get("totalDocs").asLong(), numTotalDocs);
    assertEquals(firstQueryResponse.get("numDocsScanned").asInt(), firstQueryResult);
    secondQueryResponse = postQuery(TEST_STAR_TREE_QUERY_2);
    assertEquals(secondQueryResponse.get("aggregationResults").get(0).get("value").asInt(), secondQueryResult);
    assertEquals(secondQueryResponse.get("totalDocs").asLong(), numTotalDocs);
    assertEquals(secondQueryResponse.get("numDocsScanned").asInt(), NUM_SEGMENTS);

    // Should be able to use the star-tree with an additional match-all predicate on another dimension
    secondQueryResponse = postQuery(TEST_STAR_TREE_QUERY_2 + " AND DaysSinceEpoch > 16070");
    assertEquals(secondQueryResponse.get("aggregationResults").get(0).get("value").asInt(), secondQueryResult);
    assertEquals(secondQueryResponse.get("totalDocs").asLong(), numTotalDocs);
    assertEquals(secondQueryResponse.get("numDocsScanned").asInt(), NUM_SEGMENTS);

    // Remove the star-tree index config and trigger reload
    indexingConfig.setStarTreeIndexConfigs(null);
    updateTableConfig(tableConfig);
    reloadOfflineTable(getTableName());

    TestUtils.waitForCondition(aVoid -> {
      try {
        JsonNode queryResponse = postQuery(TEST_STAR_TREE_QUERY_2);
        // Result should not change during reload
        assertEquals(queryResponse.get("aggregationResults").get(0).get("value").asInt(), secondQueryResult);
        // Total docs should not change during reload
        assertEquals(queryResponse.get("totalDocs").asLong(), numTotalDocs);
        // Without star-tree, 'numDocsScanned' should be the same as the 'COUNT(*)' result
        return queryResponse.get("numDocsScanned").asInt() == secondQueryResult;
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }, 600_000L, "Failed to remove star-tree index");
    assertEquals(getTableSize(getTableName()), tableSizeWithDefaultIndex);

    // First query should not be able to use the star-tree
    firstQueryResponse = postQuery(TEST_STAR_TREE_QUERY_1);
    assertEquals(firstQueryResponse.get("numDocsScanned").asInt(), firstQueryResult);

    // Reload again should have no effect
    reloadOfflineTable(getTableName());
    firstQueryResponse = postQuery(TEST_STAR_TREE_QUERY_1);
    assertEquals(firstQueryResponse.get("aggregationResults").get(0).get("value").asInt(), firstQueryResult);
    assertEquals(firstQueryResponse.get("totalDocs").asLong(), numTotalDocs);
    assertEquals(firstQueryResponse.get("numDocsScanned").asInt(), firstQueryResult);
    secondQueryResponse = postQuery(TEST_STAR_TREE_QUERY_2);
    assertEquals(secondQueryResponse.get("aggregationResults").get(0).get("value").asInt(), secondQueryResult);
    assertEquals(secondQueryResponse.get("totalDocs").asLong(), numTotalDocs);
    assertEquals(secondQueryResponse.get("numDocsScanned").asInt(), secondQueryResult);
  }

}