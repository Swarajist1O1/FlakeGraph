class DummyClass_104641 {
  @Test(dependsOnMethods = "testAggregateMetadataAPI")
  public void testDefaultColumns()
      throws Exception {
    long numTotalDocs = getCountStarResult();

    reloadWithExtraColumns();
    JsonNode queryResponse = postQuery(SELECT_STAR_QUERY);
    assertEquals(queryResponse.get("totalDocs").asLong(), numTotalDocs);
    assertEquals(queryResponse.get("selectionResults").get("columns").size(), 91);

    testNewAddedColumns();

    reloadWithMissingColumns();
    queryResponse = postQuery(SELECT_STAR_QUERY);
    assertEquals(queryResponse.get("totalDocs").asLong(), numTotalDocs);
    assertEquals(queryResponse.get("selectionResults").get("columns").size(), 75);

    reloadWithRegularColumns();
    queryResponse = postQuery(SELECT_STAR_QUERY);
    assertEquals(queryResponse.get("totalDocs").asLong(), numTotalDocs);
    assertEquals(queryResponse.get("selectionResults").get("columns").size(), 79);

    _tableSizeAfterRemovingIndex = getTableSize(getTableName());
  }

}