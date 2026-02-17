class DummyClass_104648 {
  @Test
  public void testCaseStatementInSelectionWithTransformFunctionInThen()
      throws Exception {
    String sqlQuery =
        "SELECT ArrDelay, CASE WHEN ArrDelay > 0 THEN ArrDelay WHEN ArrDelay < 0 THEN ArrDelay * -1 ELSE 0 END AS "
            + "ArrTimeDiff FROM mytable LIMIT 1000";
    JsonNode response = postSqlQuery(sqlQuery, _brokerBaseApiUrl);
    JsonNode rows = response.get("resultTable").get("rows");
    assertEquals(response.get("exceptions").size(), 0);
    for (int i = 0; i < rows.size(); i++) {
      int arrDelay = rows.get(i).get(0).asInt();
      int arrDelayDiff = rows.get(i).get(1).asInt();
      if (arrDelay > 0) {
        assertEquals(arrDelay, arrDelayDiff);
      } else {
        assertEquals(arrDelay, arrDelayDiff * -1);
      }
    }
  }

}