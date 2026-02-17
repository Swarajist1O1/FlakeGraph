class DummyClass_104631 {
  @Test
  public void testRefreshTableConfigAndQueryTimeout()
      throws Exception {
    // Set timeout as 5ms so that query will timeout
    TableConfig tableConfig = getOfflineTableConfig();
    tableConfig.setQueryConfig(new QueryConfig(5L));
    updateTableConfig(tableConfig);

    // Wait for at most 1 minute for broker to receive and process the table config refresh message
    TestUtils.waitForCondition(aVoid -> {
      try {
        JsonNode queryResponse = postQuery(TEST_TIMEOUT_QUERY);
        JsonNode exceptions = queryResponse.get("exceptions");
        if (exceptions.isEmpty()) {
          return false;
        }
        int errorCode = exceptions.get(0).get("errorCode").asInt();
        if (errorCode == QueryException.BROKER_TIMEOUT_ERROR_CODE) {
          // Timed out on broker side
          return true;
        }
        if (errorCode == QueryException.SERVER_NOT_RESPONDING_ERROR_CODE) {
          // Timed out on server side
          int numServersQueried = queryResponse.get("numServersQueried").asInt();
          int numServersResponded = queryResponse.get("numServersResponded").asInt();
          int numDocsScanned = queryResponse.get("numDocsScanned").asInt();
          return numServersQueried == getNumServers() && numServersResponded == 0 && numDocsScanned == 0;
        }
        return false;
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }, 60_000L, "Failed to refresh table config");

    // Remove timeout so that query will finish
    tableConfig.setQueryConfig(null);
    updateTableConfig(tableConfig);

    // Wait for at most 1 minute for broker to receive and process the table config refresh message
    TestUtils.waitForCondition(aVoid -> {
      try {
        JsonNode queryResponse = postQuery(TEST_TIMEOUT_QUERY);
        JsonNode exceptions = queryResponse.get("exceptions");
        if (!exceptions.isEmpty()) {
          return false;
        }
        int numServersQueried = queryResponse.get("numServersQueried").asInt();
        int numServersResponded = queryResponse.get("numServersResponded").asInt();
        int numDocsScanned = queryResponse.get("numDocsScanned").asInt();
        return numServersQueried == getNumServers() && numServersResponded == getNumServers()
            && numDocsScanned == getCountStarResult();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }, 60_000L, "Failed to refresh table config");
  }

}