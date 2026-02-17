class DummyClass_104645 {
  @Test
  public void testSelectionUDF()
      throws Exception {
    String pqlQuery = "SELECT DaysSinceEpoch, timeConvert(DaysSinceEpoch,'DAYS','SECONDS') FROM mytable";
    JsonNode response = postQuery(pqlQuery);
    ArrayNode selectionResults = (ArrayNode) response.get("selectionResults").get("results");
    assertNotNull(selectionResults);
    assertFalse(selectionResults.isEmpty());
    for (int i = 0; i < selectionResults.size(); i++) {
      long daysSinceEpoch = selectionResults.get(i).get(0).asLong();
      long secondsSinceEpoch = selectionResults.get(i).get(1).asLong();
      assertEquals(daysSinceEpoch * 24 * 60 * 60, secondsSinceEpoch);
    }

    pqlQuery =
        "SELECT DaysSinceEpoch, timeConvert(DaysSinceEpoch,'DAYS','SECONDS') FROM mytable order by DaysSinceEpoch "
            + "limit 10000";
    response = postQuery(pqlQuery);
    selectionResults = (ArrayNode) response.get("selectionResults").get("results");
    assertNotNull(selectionResults);
    assertFalse(selectionResults.isEmpty());
    long prevValue = -1;
    for (int i = 0; i < selectionResults.size(); i++) {
      long daysSinceEpoch = selectionResults.get(i).get(0).asLong();
      long secondsSinceEpoch = selectionResults.get(i).get(1).asLong();
      assertEquals(daysSinceEpoch * 24 * 60 * 60, secondsSinceEpoch);
      assertTrue(daysSinceEpoch >= prevValue);
      prevValue = daysSinceEpoch;
    }

    pqlQuery =
        "SELECT DaysSinceEpoch, timeConvert(DaysSinceEpoch,'DAYS','SECONDS') FROM mytable order by timeConvert"
            + "(DaysSinceEpoch,'DAYS','SECONDS') DESC limit 10000";
    response = postQuery(pqlQuery);
    selectionResults = (ArrayNode) response.get("selectionResults").get("results");
    assertNotNull(selectionResults);
    assertFalse(selectionResults.isEmpty());
    prevValue = Long.MAX_VALUE;
    for (int i = 0; i < selectionResults.size(); i++) {
      long daysSinceEpoch = selectionResults.get(i).get(0).asLong();
      long secondsSinceEpoch = selectionResults.get(i).get(1).asLong();
      assertEquals(daysSinceEpoch * 24 * 60 * 60, secondsSinceEpoch);
      assertTrue(secondsSinceEpoch <= prevValue);
      prevValue = secondsSinceEpoch;
    }
  }

}