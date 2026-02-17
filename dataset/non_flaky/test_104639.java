class DummyClass_104639 {
  @Test
  public void testServerErrorWithBrokerTimeout()
      throws Exception {
    // Set query timeout
    long queryTimeout = 5000;
    TableConfig tableConfig = getOfflineTableConfig();
    tableConfig.setQueryConfig(new QueryConfig(queryTimeout));
    updateTableConfig(tableConfig);

    long startTime = System.currentTimeMillis();
    // The query below will fail execution due to JSON_MATCH on column without json index
    JsonNode queryResponse = postSqlQuery("SELECT count(*) FROM mytable WHERE JSON_MATCH(Dest, '$=123')");

    assertTrue(System.currentTimeMillis() - startTime < queryTimeout);
    assertTrue(queryResponse.get("exceptions").get(0).get("message").toString().startsWith("\"QueryExecutionError"));

    // Remove timeout
    tableConfig.setQueryConfig(null);
    updateTableConfig(tableConfig);
  }

}