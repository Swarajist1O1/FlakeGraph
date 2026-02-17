class DummyClass_104647 {
  @Test
  public void testCaseStatementInSelection()
      throws Exception {
    List<String> origins = Arrays
        .asList("ATL", "ORD", "DFW", "DEN", "LAX", "IAH", "SFO", "PHX", "LAS", "EWR", "MCO", "BOS", "SLC", "SEA", "MSP",
            "CLT", "LGA", "DTW", "JFK", "BWI");
    StringBuilder caseStatementBuilder = new StringBuilder("CASE ");
    for (int i = 0; i < origins.size(); i++) {
      // WHEN origin = 'ATL' THEN 1
      // WHEN origin = 'ORD' THEN 2
      // WHEN origin = 'DFW' THEN 3
      // ....
      caseStatementBuilder.append(String.format("WHEN origin = '%s' THEN %d ", origins.get(i), i + 1));
    }
    caseStatementBuilder.append("ELSE 0 END");
    String sqlQuery = "SELECT origin, " + caseStatementBuilder + " AS origin_code FROM mytable LIMIT 1000";
    JsonNode response = postSqlQuery(sqlQuery, _brokerBaseApiUrl);
    JsonNode rows = response.get("resultTable").get("rows");
    assertEquals(response.get("exceptions").size(), 0);
    for (int i = 0; i < rows.size(); i++) {
      String origin = rows.get(i).get(0).asText();
      int originCode = rows.get(i).get(1).asInt();
      if (originCode > 0) {
        assertEquals(origin, origins.get(originCode - 1));
      } else {
        assertFalse(origins.contains(origin));
      }
    }
  }

}