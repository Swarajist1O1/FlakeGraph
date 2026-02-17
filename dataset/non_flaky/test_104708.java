class DummyClass_104708 {
  @Test
  public void testPqlQueries()
      throws Exception {

    //Selection Query
    String pqlQuery = "Select " + MY_MAP_STR_FIELD_NAME + " from " + DEFAULT_TABLE_NAME;
    JsonNode pinotResponse = postQuery(pqlQuery);
    ArrayNode selectionResults = (ArrayNode) pinotResponse.get("selectionResults").get("results");
    Assert.assertNotNull(selectionResults);
    Assert.assertFalse(selectionResults.isEmpty());
    for (int i = 0; i < selectionResults.size(); i++) {
      String value = selectionResults.get(i).get(0).textValue();
      Assert.assertTrue(value.indexOf("-k1-") > 0);
    }

    //Filter Query
    pqlQuery = "Select jsonExtractScalar(myMapStr,'$.k1','STRING') from " + DEFAULT_TABLE_NAME
        + "  where jsonExtractScalar(myMapStr,'$.k1','STRING') = 'value-k1-0'";
    pinotResponse = postQuery(pqlQuery);
    selectionResults = (ArrayNode) pinotResponse.get("selectionResults").get("results");
    Assert.assertNotNull(selectionResults);
    Assert.assertFalse(selectionResults.isEmpty());
    for (int i = 0; i < selectionResults.size(); i++) {
      String value = selectionResults.get(i).get(0).textValue();
      Assert.assertEquals(value, "value-k1-0");
    }
    pqlQuery =
        "Select " + MY_MAP_STR_K1_FIELD_NAME + " from " + DEFAULT_TABLE_NAME + "  where " + MY_MAP_STR_K1_FIELD_NAME
            + " = 'value-k1-0'";
    pinotResponse = postQuery(pqlQuery);
    selectionResults = (ArrayNode) pinotResponse.get("selectionResults").get("results");
    Assert.assertNotNull(selectionResults);
    Assert.assertFalse(selectionResults.isEmpty());
    for (int i = 0; i < selectionResults.size(); i++) {
      String value = selectionResults.get(i).get(0).textValue();
      Assert.assertEquals(value, "value-k1-0");
    }

    //selection order by
    pqlQuery = "Select jsonExtractScalar(myMapStr,'$.k1','STRING') from " + DEFAULT_TABLE_NAME
        + " order by jsonExtractScalar(myMapStr,'$.k1','STRING')";
    pinotResponse = postQuery(pqlQuery);
    selectionResults = (ArrayNode) pinotResponse.get("selectionResults").get("results");
    Assert.assertNotNull(selectionResults);
    Assert.assertFalse(selectionResults.isEmpty());
    for (int i = 0; i < selectionResults.size(); i++) {
      String value = selectionResults.get(i).get(0).textValue();
      Assert.assertTrue(value.indexOf("-k1-") > 0);
    }
    pqlQuery =
        "Select " + MY_MAP_STR_K1_FIELD_NAME + " from " + DEFAULT_TABLE_NAME + " order by " + MY_MAP_STR_K1_FIELD_NAME;
    pinotResponse = postQuery(pqlQuery);
    selectionResults = (ArrayNode) pinotResponse.get("selectionResults").get("results");
    Assert.assertNotNull(selectionResults);
    Assert.assertFalse(selectionResults.isEmpty());
    for (int i = 0; i < selectionResults.size(); i++) {
      String value = selectionResults.get(i).get(0).textValue();
      Assert.assertTrue(value.indexOf("-k1-") > 0);
    }

    //Group By Query
    pqlQuery = "Select count(*) from " + DEFAULT_TABLE_NAME + " group by jsonExtractScalar(myMapStr,'$.k1','STRING')";
    pinotResponse = postQuery(pqlQuery);
    Assert.assertNotNull(pinotResponse.get("aggregationResults"));
    JsonNode groupByResult = pinotResponse.get("aggregationResults").get(0).get("groupByResult");
    Assert.assertNotNull(groupByResult);
    Assert.assertTrue(groupByResult.isArray());
    Assert.assertFalse(groupByResult.isEmpty());

    pqlQuery = "Select count(*) from " + DEFAULT_TABLE_NAME + " group by " + MY_MAP_STR_K1_FIELD_NAME;
    pinotResponse = postQuery(pqlQuery);
    Assert.assertNotNull(pinotResponse.get("aggregationResults"));
    groupByResult = pinotResponse.get("aggregationResults").get(0).get("groupByResult");
    Assert.assertNotNull(groupByResult);
    Assert.assertTrue(groupByResult.isArray());
    Assert.assertFalse(groupByResult.isEmpty());
  }

}