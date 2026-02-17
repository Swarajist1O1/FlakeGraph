class DummyClass_104644 {
  @Test
  public void testAggregationUDF()
      throws Exception {

    String pqlQuery = "SELECT MAX(timeConvert(DaysSinceEpoch,'DAYS','SECONDS')) FROM mytable";
    JsonNode response = postQuery(pqlQuery);
    JsonNode aggregationResult = response.get("aggregationResults").get(0);
    assertEquals(aggregationResult.get("function").asText(), "max_timeconvert(DaysSinceEpoch,'DAYS','SECONDS')");
    assertEquals(aggregationResult.get("value").asDouble(), 16435.0 * 24 * 3600);

    pqlQuery = "SELECT MIN(div(DaysSinceEpoch,2)) FROM mytable";
    response = postQuery(pqlQuery);
    aggregationResult = response.get("aggregationResults").get(0);
    assertEquals(aggregationResult.get("function").asText(), "min_div(DaysSinceEpoch,'2')");
    assertEquals(aggregationResult.get("value").asDouble(), 16071.0 / 2);
  }

}