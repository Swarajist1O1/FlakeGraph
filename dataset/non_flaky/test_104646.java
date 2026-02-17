class DummyClass_104646 {
  @Test
  public void testFilterUDF()
      throws Exception {
    int daysSinceEpoch = 16138;
    long secondsSinceEpoch = 16138 * 24 * 60 * 60;

    String pqlQuery;
    pqlQuery = "SELECT count(*) FROM mytable WHERE DaysSinceEpoch = " + daysSinceEpoch;
    long expectedResult = postQuery(pqlQuery).get("aggregationResults").get(0).get("value").asLong();

    pqlQuery = "SELECT count(*) FROM mytable WHERE timeConvert(DaysSinceEpoch,'DAYS','SECONDS') = " + secondsSinceEpoch;
    assertEquals(postQuery(pqlQuery).get("aggregationResults").get(0).get("value").asLong(), expectedResult);

    pqlQuery = "SELECT count(*) FROM mytable WHERE DaysSinceEpoch = " + daysSinceEpoch
        + " OR timeConvert(DaysSinceEpoch,'DAYS','SECONDS') = " + secondsSinceEpoch;
    assertEquals(postQuery(pqlQuery).get("aggregationResults").get(0).get("value").asLong(), expectedResult);

    pqlQuery = "SELECT count(*) FROM mytable WHERE DaysSinceEpoch = " + daysSinceEpoch
        + " AND timeConvert(DaysSinceEpoch,'DAYS','SECONDS') = " + secondsSinceEpoch;
    assertEquals(postQuery(pqlQuery).get("aggregationResults").get(0).get("value").asLong(), expectedResult);

    pqlQuery =
        "SELECT count(*) FROM mytable WHERE DIV(timeConvert(DaysSinceEpoch,'DAYS','SECONDS'),1) = " + secondsSinceEpoch;
    assertEquals(postQuery(pqlQuery).get("aggregationResults").get(0).get("value").asLong(), expectedResult);

    pqlQuery = String
        .format("SELECT count(*) FROM mytable WHERE timeConvert(DaysSinceEpoch,'DAYS','SECONDS') IN (%d, %d)",
            secondsSinceEpoch - 100, secondsSinceEpoch);
    assertEquals(postQuery(pqlQuery).get("aggregationResults").get(0).get("value").asLong(), expectedResult);

    pqlQuery = String
        .format("SELECT count(*) FROM mytable WHERE timeConvert(DaysSinceEpoch,'DAYS','SECONDS') BETWEEN %d AND %d",
            secondsSinceEpoch - 100, secondsSinceEpoch);
    assertEquals(postQuery(pqlQuery).get("aggregationResults").get(0).get("value").asLong(), expectedResult);
  }

}