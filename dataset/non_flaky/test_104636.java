class DummyClass_104636 {
  @Test
  public void testLiteralOnlyFunc()
      throws Exception {
    long currentTsMin = System.currentTimeMillis();
    long oneHourAgoTsMin = currentTsMin - ONE_HOUR_IN_MS;
    String sqlQuery =
        "SELECT 1, now() as currentTs, ago('PT1H') as oneHourAgoTs, 'abc', toDateTime(now(), 'yyyy-MM-dd z') as "
            + "today, now(), ago('PT1H')";
    JsonNode response = postSqlQuery(sqlQuery, _brokerBaseApiUrl);
    long currentTsMax = System.currentTimeMillis();
    long oneHourAgoTsMax = currentTsMax - ONE_HOUR_IN_MS;

    assertEquals(response.get("resultTable").get("dataSchema").get("columnNames").get(0).asText(), "1");
    assertEquals(response.get("resultTable").get("dataSchema").get("columnNames").get(1).asText(), "currentTs");
    assertEquals(response.get("resultTable").get("dataSchema").get("columnNames").get(2).asText(), "oneHourAgoTs");
    assertEquals(response.get("resultTable").get("dataSchema").get("columnNames").get(3).asText(), "abc");
    assertEquals(response.get("resultTable").get("dataSchema").get("columnNames").get(4).asText(), "today");
    String nowColumnName = response.get("resultTable").get("dataSchema").get("columnNames").get(5).asText();
    String oneHourAgoColumnName = response.get("resultTable").get("dataSchema").get("columnNames").get(6).asText();
    assertTrue(Long.parseLong(nowColumnName) > currentTsMin);
    assertTrue(Long.parseLong(nowColumnName) < currentTsMax);
    assertTrue(Long.parseLong(oneHourAgoColumnName) > oneHourAgoTsMin);
    assertTrue(Long.parseLong(oneHourAgoColumnName) < oneHourAgoTsMax);

    assertEquals(response.get("resultTable").get("dataSchema").get("columnDataTypes").get(0).asText(), "LONG");
    assertEquals(response.get("resultTable").get("dataSchema").get("columnDataTypes").get(1).asText(), "LONG");
    assertEquals(response.get("resultTable").get("dataSchema").get("columnDataTypes").get(2).asText(), "LONG");
    assertEquals(response.get("resultTable").get("dataSchema").get("columnDataTypes").get(3).asText(), "STRING");
    assertEquals(response.get("resultTable").get("dataSchema").get("columnDataTypes").get(4).asText(), "STRING");
    assertEquals(response.get("resultTable").get("dataSchema").get("columnDataTypes").get(5).asText(), "LONG");
    assertEquals(response.get("resultTable").get("dataSchema").get("columnDataTypes").get(6).asText(), "LONG");

    int first = response.get("resultTable").get("rows").get(0).get(0).asInt();
    long second = response.get("resultTable").get("rows").get(0).get(1).asLong();
    long third = response.get("resultTable").get("rows").get(0).get(2).asLong();
    String fourth = response.get("resultTable").get("rows").get(0).get(3).asText();
    assertEquals(first, 1);
    assertTrue(second > currentTsMin);
    assertTrue(second < currentTsMax);
    assertTrue(third > oneHourAgoTsMin);
    assertTrue(third < oneHourAgoTsMax);
    assertEquals(fourth, "abc");
    String todayStr = response.get("resultTable").get("rows").get(0).get(4).asText();
    String expectedTodayStr =
        Instant.now().atZone(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd z"));
    assertEquals(todayStr, expectedTodayStr);
    long nowValue = response.get("resultTable").get("rows").get(0).get(5).asLong();
    assertEquals(nowValue, Long.parseLong(nowColumnName));
    long oneHourAgoValue = response.get("resultTable").get("rows").get(0).get(6).asLong();
    assertEquals(oneHourAgoValue, Long.parseLong(oneHourAgoColumnName));
  }

}