class DummyClass_104662 {
  @Test
  public void testAggregationFunctionsWithUnderscore()
      throws Exception {
    String query;

    // The Accurate value is 6538.
    query = "SELECT distinct_count(FlightNum) FROM mytable ";
    assertEquals(postQuery(query).get("aggregationResults").get(0).get("value").asLong(), 6538);
    assertEquals(postSqlQuery(query, _brokerBaseApiUrl).get("resultTable").get("rows").get(0).get(0).asLong(), 6538);

    // The Accurate value is 6538.
    query = "SELECT c_o_u_n_t(FlightNum) FROM mytable ";
    assertEquals(postQuery(query).get("aggregationResults").get(0).get("value").asLong(), 115545);
    assertEquals(postSqlQuery(query, _brokerBaseApiUrl).get("resultTable").get("rows").get(0).get(0).asLong(), 115545);
  }

}