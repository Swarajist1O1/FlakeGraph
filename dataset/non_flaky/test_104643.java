class DummyClass_104643 {
  @Test
  public void testGroupByUDF()
      throws Exception {
    String pqlQuery = "SELECT COUNT(*) FROM mytable GROUP BY timeConvert(DaysSinceEpoch,'DAYS','SECONDS')";
    JsonNode response = postQuery(pqlQuery);
    JsonNode groupByResult = response.get("aggregationResults").get(0);
    JsonNode groupByEntry = groupByResult.get("groupByResult").get(0);
    assertEquals(groupByEntry.get("value").asDouble(), 605.0);
    assertEquals(groupByEntry.get("group").get(0).asInt(), 16138 * 24 * 3600);
    assertEquals(groupByResult.get("groupByColumns").get(0).asText(), "timeconvert(DaysSinceEpoch,'DAYS','SECONDS')");

    pqlQuery =
        "SELECT COUNT(*) FROM mytable GROUP BY dateTimeConvert(DaysSinceEpoch,'1:DAYS:EPOCH','1:HOURS:EPOCH',"
            + "'1:HOURS')";
    response = postQuery(pqlQuery);
    groupByResult = response.get("aggregationResults").get(0);
    groupByEntry = groupByResult.get("groupByResult").get(0);
    assertEquals(groupByEntry.get("value").asDouble(), 605.0);
    assertEquals(groupByEntry.get("group").get(0).asInt(), 16138 * 24);
    assertEquals(groupByResult.get("groupByColumns").get(0).asText(),
        "datetimeconvert(DaysSinceEpoch,'1:DAYS:EPOCH','1:HOURS:EPOCH','1:HOURS')");

    pqlQuery = "SELECT COUNT(*) FROM mytable GROUP BY add(DaysSinceEpoch,DaysSinceEpoch,15)";
    response = postQuery(pqlQuery);
    groupByResult = response.get("aggregationResults").get(0);
    groupByEntry = groupByResult.get("groupByResult").get(0);
    assertEquals(groupByEntry.get("value").asDouble(), 605.0);
    assertEquals(groupByEntry.get("group").get(0).asDouble(), 16138.0 + 16138 + 15);
    assertEquals(groupByResult.get("groupByColumns").get(0).asText(), "add(DaysSinceEpoch,DaysSinceEpoch,'15')");

    pqlQuery = "SELECT COUNT(*) FROM mytable GROUP BY sub(DaysSinceEpoch,25)";
    response = postQuery(pqlQuery);
    groupByResult = response.get("aggregationResults").get(0);
    groupByEntry = groupByResult.get("groupByResult").get(0);
    assertEquals(groupByEntry.get("value").asDouble(), 605.0);
    assertEquals(groupByEntry.get("group").get(0).asDouble(), 16138.0 - 25);
    assertEquals(groupByResult.get("groupByColumns").get(0).asText(), "sub(DaysSinceEpoch,'25')");

    pqlQuery = "SELECT COUNT(*) FROM mytable GROUP BY mult(DaysSinceEpoch,24,3600)";
    response = postQuery(pqlQuery);
    groupByResult = response.get("aggregationResults").get(0);
    groupByEntry = groupByResult.get("groupByResult").get(0);
    assertEquals(groupByEntry.get("value").asDouble(), 605.0);
    assertEquals(groupByEntry.get("group").get(0).asDouble(), 16138.0 * 24 * 3600);
    assertEquals(groupByResult.get("groupByColumns").get(0).asText(), "mult(DaysSinceEpoch,'24','3600')");

    pqlQuery = "SELECT COUNT(*) FROM mytable GROUP BY div(DaysSinceEpoch,2)";
    response = postQuery(pqlQuery);
    groupByResult = response.get("aggregationResults").get(0);
    groupByEntry = groupByResult.get("groupByResult").get(0);
    assertEquals(groupByEntry.get("value").asDouble(), 605.0);
    assertEquals(groupByEntry.get("group").get(0).asDouble(), 16138.0 / 2);
    assertEquals(groupByResult.get("groupByColumns").get(0).asText(), "div(DaysSinceEpoch,'2')");

    pqlQuery = "SELECT COUNT(*) FROM mytable GROUP BY arrayLength(DivAirports)";
    response = postQuery(pqlQuery);
    groupByResult = response.get("aggregationResults").get(0);
    groupByEntry = groupByResult.get("groupByResult").get(0);
    assertEquals(groupByEntry.get("value").asDouble(), 115545.0);
    assertEquals(groupByEntry.get("group").get(0).asText(), "5");
    assertEquals(groupByResult.get("groupByColumns").get(0).asText(), "arraylength(DivAirports)");

    pqlQuery = "SELECT COUNT(*) FROM mytable GROUP BY arrayLength(valueIn(DivAirports,'DFW','ORD'))";
    response = postQuery(pqlQuery);
    groupByResult = response.get("aggregationResults").get(0);
    groupByEntry = groupByResult.get("groupByResult").get(0);
    assertEquals(groupByEntry.get("value").asDouble(), 114895.0);
    assertEquals(groupByEntry.get("group").get(0).asText(), "0");
    groupByEntry = groupByResult.get("groupByResult").get(1);
    assertEquals(groupByEntry.get("value").asDouble(), 648.0);
    assertEquals(groupByEntry.get("group").get(0).asText(), "1");
    groupByEntry = groupByResult.get("groupByResult").get(2);
    assertEquals(groupByEntry.get("value").asDouble(), 2.0);
    assertEquals(groupByEntry.get("group").get(0).asText(), "2");
    assertEquals(groupByResult.get("groupByColumns").get(0).asText(), "arraylength(valuein(DivAirports,'DFW','ORD'))");

    pqlQuery = "SELECT COUNT(*) FROM mytable GROUP BY valueIn(DivAirports,'DFW','ORD')";
    response = postQuery(pqlQuery);
    groupByResult = response.get("aggregationResults").get(0);
    groupByEntry = groupByResult.get("groupByResult").get(0);
    assertEquals(groupByEntry.get("value").asDouble(), 336.0);
    assertEquals(groupByEntry.get("group").get(0).asText(), "ORD");
    assertEquals(groupByResult.get("groupByColumns").get(0).asText(), "valuein(DivAirports,'DFW','ORD')");

    pqlQuery = "SELECT MAX(timeConvert(DaysSinceEpoch,'DAYS','SECONDS')) FROM mytable";
    response = postQuery(pqlQuery);
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