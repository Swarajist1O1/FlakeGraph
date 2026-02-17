class DummyClass_104649 {
  @Test
  public void testCaseStatementWithLogicalTransformFunction()
      throws Exception {
    String sqlQuery = "SELECT ArrDelay" + ", CASE WHEN ArrDelay > 50 OR ArrDelay < 10 THEN 10 ELSE 0 END"
        + ", CASE WHEN ArrDelay < 50 AND ArrDelay >= 10 THEN 10 ELSE 0 END" + " FROM mytable LIMIT 1000";
    JsonNode response = postSqlQuery(sqlQuery, _brokerBaseApiUrl);
    JsonNode rows = response.get("resultTable").get("rows");
    assertEquals(response.get("exceptions").size(), 0);
    for (int i = 0; i < rows.size(); i++) {
      int row0 = rows.get(i).get(0).asInt();
      int row1 = rows.get(i).get(1).asInt();
      int row2 = rows.get(i).get(2).asInt();
      if (row0 > 50 || row0 < 10) {
        assertEquals(row1, 10);
      } else {
        assertEquals(row1, 0);
      }
      if (row0 < 50 && row0 >= 10) {
        assertEquals(row2, 10);
      } else {
        assertEquals(row2, 0);
      }
    }
  }

}