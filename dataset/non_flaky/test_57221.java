class DummyClass_57221 {
  @Test
  public void testReconSchemaCreated() throws Exception {
    Connection connection = getConnection();
    // Verify table definition
    DatabaseMetaData metaData = connection.getMetaData();
    ResultSet resultSet = metaData.getColumns(null, null,
        CLUSTER_GROWTH_DAILY_TABLE_NAME, null);

    List<Pair<String, Integer>> expectedPairs = new ArrayList<>();

    expectedPairs.add(new ImmutablePair<>("timestamp", Types.TIMESTAMP));
    expectedPairs.add(new ImmutablePair<>("datanode_id", Types.INTEGER));
    expectedPairs.add(new ImmutablePair<>("datanode_host", Types.VARCHAR));
    expectedPairs.add(new ImmutablePair<>("rack_id", Types.VARCHAR));
    expectedPairs.add(new ImmutablePair<>("available_size", Types.BIGINT));
    expectedPairs.add(new ImmutablePair<>("used_size", Types.BIGINT));
    expectedPairs.add(new ImmutablePair<>("container_count", Types.INTEGER));
    expectedPairs.add(new ImmutablePair<>("block_count", Types.INTEGER));

    List<Pair<String, Integer>> actualPairs = new ArrayList<>();

    while (resultSet.next()) {
      actualPairs.add(new ImmutablePair<>(resultSet.getString("COLUMN_NAME"),
          resultSet.getInt("DATA_TYPE")));
    }

    Assert.assertEquals(8, actualPairs.size());
    Assert.assertEquals(expectedPairs, actualPairs);

    ResultSet resultSetFileCount = metaData.getColumns(null, null,
        FILE_COUNT_BY_SIZE_TABLE_NAME, null);

    List<Pair<String, Integer>> expectedPairsFileCount = new ArrayList<>();
    expectedPairsFileCount.add(
        new ImmutablePair<>("volume", Types.VARCHAR));
    expectedPairsFileCount.add(
        new ImmutablePair<>("bucket", Types.VARCHAR));
    expectedPairsFileCount.add(
        new ImmutablePair<>("file_size", Types.BIGINT));
    expectedPairsFileCount.add(
        new ImmutablePair<>("count", Types.BIGINT));

    List<Pair<String, Integer>> actualPairsFileCount = new ArrayList<>();
    while(resultSetFileCount.next()) {
      actualPairsFileCount.add(new ImmutablePair<>(resultSetFileCount.getString(
          "COLUMN_NAME"), resultSetFileCount.getInt(
              "DATA_TYPE")));
    }
    assertEquals("Unexpected number of columns",
        4, actualPairsFileCount.size());
    assertEquals("Columns Do not Match ",
        expectedPairsFileCount, actualPairsFileCount);
  }

}