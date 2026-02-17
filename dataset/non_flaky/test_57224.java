class DummyClass_57224 {
  @Test
  public void testSchemaCreated() throws Exception {

    Connection connection = getConnection();
    // Verify table definition
    DatabaseMetaData metaData = connection.getMetaData();
    ResultSet resultSet = metaData.getColumns(null, null,
        RECON_TASK_STATUS_TABLE_NAME, null);

    List<Pair<String, Integer>> expectedPairs = new ArrayList<>();

    expectedPairs.add(new ImmutablePair<>("task_name", Types.VARCHAR));
    expectedPairs.add(new ImmutablePair<>("last_updated_timestamp",
        Types.BIGINT));
    expectedPairs.add(new ImmutablePair<>("last_updated_seq_number",
        Types.BIGINT));

    List<Pair<String, Integer>> actualPairs = new ArrayList<>();

    while (resultSet.next()) {
      actualPairs.add(new ImmutablePair<>(
          resultSet.getString("COLUMN_NAME"),
          resultSet.getInt("DATA_TYPE")));
    }

    Assert.assertEquals(3, actualPairs.size());
    Assert.assertEquals(expectedPairs, actualPairs);
  }

}