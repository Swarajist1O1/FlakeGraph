class DummyClass_104635 {
  @Test
  public void testTimeFunc()
      throws Exception {
    String sqlQuery = "SELECT toDateTime(now(), 'yyyy-MM-dd z'), toDateTime(ago('PT1H'), 'yyyy-MM-dd z') FROM mytable";
    JsonNode response = postSqlQuery(sqlQuery, _brokerBaseApiUrl);
    String todayStr = response.get("resultTable").get("rows").get(0).get(0).asText();
    String expectedTodayStr =
        Instant.now().atZone(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd z"));
    assertEquals(todayStr, expectedTodayStr);

    String oneHourAgoTodayStr = response.get("resultTable").get("rows").get(0).get(1).asText();
    String expectedOneHourAgoTodayStr = Instant.now().minus(Duration.parse("PT1H")).atZone(ZoneId.of("UTC"))
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd z"));
    assertEquals(oneHourAgoTodayStr, expectedOneHourAgoTodayStr);
  }

}