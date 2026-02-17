class DummyClass_104661 {
  @Test
  public void testDistinctCountHll()
      throws Exception {
    String query;

    // The Accurate value is 6538.
    query = "SELECT distinctCount(FlightNum) FROM mytable ";
    assertEquals(postQuery(query).get("aggregationResults").get(0).get("value").asLong(), 6538);
    assertEquals(postSqlQuery(query, _brokerBaseApiUrl).get("resultTable").get("rows").get(0).get(0).asLong(), 6538);

    // Expected distinctCountHll with different log2m value from 2 to 19. The Accurate value is 6538.
    long[] expectedResults = new long[]{
        3504, 6347, 8877, 9729, 9046, 7672, 7538, 6993, 6649, 6651, 6553, 6525, 6459, 6523, 6532, 6544, 6538, 6539
    };

    for (int i = 2; i < 20; i++) {
      query = String.format("SELECT distinctCountHLL(FlightNum, %d) FROM mytable ", i);
      assertEquals(postQuery(query).get("aggregationResults").get(0).get("value").asLong(), expectedResults[i - 2]);
      assertEquals(postSqlQuery(query, _brokerBaseApiUrl).get("resultTable").get("rows").get(0).get(0).asLong(),
          expectedResults[i - 2]);
    }

    // Default HLL is set as log2m=12
    query = "SELECT distinctCountHLL(FlightNum) FROM mytable ";
    assertEquals(postQuery(query).get("aggregationResults").get(0).get("value").asLong(), expectedResults[10]);
    assertEquals(postSqlQuery(query, _brokerBaseApiUrl).get("resultTable").get("rows").get(0).get(0).asLong(),
        expectedResults[10]);
  }

}