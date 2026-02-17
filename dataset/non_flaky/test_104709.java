class DummyClass_104709 {
  @Test
  public void testSqlQueries()
      throws Exception {
    //Selection Query
    String sqlQuery = "Select myMapStr from " + DEFAULT_TABLE_NAME;
    JsonNode pinotResponse = postSqlQuery(sqlQuery);
    ArrayNode rows = (ArrayNode) pinotResponse.get("resultTable").get("rows");
    Assert.assertNotNull(rows);
    Assert.assertFalse(rows.isEmpty());
    for (int i = 0; i < rows.size(); i++) {
      String value = rows.get(i).get(0).textValue();
      Assert.assertTrue(value.indexOf("-k1-") > 0);
    }

    //Filter Query
    sqlQuery = "Select jsonExtractScalar(myMapStr,'$.k1','STRING') from " + DEFAULT_TABLE_NAME
        + "  where jsonExtractScalar(myMapStr,'$.k1','STRING') = 'value-k1-0'";
    pinotResponse = postSqlQuery(sqlQuery);
    rows = (ArrayNode) pinotResponse.get("resultTable").get("rows");
    Assert.assertNotNull(rows);
    Assert.assertFalse(rows.isEmpty());
    for (int i = 0; i < rows.size(); i++) {
      String value = rows.get(i).get(0).textValue();
      Assert.assertEquals(value, "value-k1-0");
    }

    //selection order by
    sqlQuery = "Select jsonExtractScalar(myMapStr,'$.k1','STRING') from " + DEFAULT_TABLE_NAME
        + " order by jsonExtractScalar(myMapStr,'$.k1','STRING')";
    pinotResponse = postSqlQuery(sqlQuery);
    rows = (ArrayNode) pinotResponse.get("resultTable").get("rows");
    Assert.assertNotNull(rows);
    Assert.assertFalse(rows.isEmpty());
    for (int i = 0; i < rows.size(); i++) {
      String value = rows.get(i).get(0).textValue();
      Assert.assertTrue(value.indexOf("-k1-") > 0);
    }

    //Group By Query
    sqlQuery = "Select jsonExtractScalar(myMapStr,'$.k1','STRING'), count(*) from " + DEFAULT_TABLE_NAME
        + " group by jsonExtractScalar(myMapStr,'$.k1','STRING')";
    pinotResponse = postSqlQuery(sqlQuery);
    Assert.assertNotNull(pinotResponse.get("resultTable"));
    rows = (ArrayNode) pinotResponse.get("resultTable").get("rows");
    for (int i = 0; i < rows.size(); i++) {
      String value = rows.get(i).get(0).textValue();
      Assert.assertTrue(value.indexOf("-k1-") > 0);
    }
  }

}