class DummyClass_104651 {
  @Test
  public void testFilterWithInvertedIndexUDF()
      throws Exception {
    int daysSinceEpoch = 16138;
    long secondsSinceEpoch = 16138 * 24 * 60 * 60;

    String[] origins = new String[]{
        "ATL", "ORD", "DFW", "DEN", "LAX", "IAH", "SFO", "PHX", "LAS", "EWR", "MCO", "BOS", "SLC", "SEA", "MSP", "CLT",
        "LGA", "DTW", "JFK", "BWI"
    };
    String pqlQuery;
    for (String origin : origins) {
      pqlQuery =
          "SELECT count(*) FROM mytable WHERE Origin = \"" + origin + "\" AND DaysSinceEpoch = " + daysSinceEpoch;
      JsonNode response1 = postQuery(pqlQuery);
      pqlQuery = "SELECT count(*) FROM mytable WHERE Origin = \"" + origin
          + "\" AND timeConvert(DaysSinceEpoch,'DAYS','SECONDS') = " + secondsSinceEpoch;
      JsonNode response2 = postQuery(pqlQuery);
      double val1 = response1.get("aggregationResults").get(0).get("value").asDouble();
      double val2 = response2.get("aggregationResults").get(0).get("value").asDouble();
      assertEquals(val1, val2);
    }
  }

}