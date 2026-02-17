class DummyClass_60877 {
  @Test
  public void testParse(String name, String input, Parsed expected)
  {
    Parser<String, Object> parser = new InfluxParser(null);
    Map<String, Object> parsed = parser.parseToMap(input);
    MatcherAssert.assertThat(
        "correct measurement name",
        parsed.get("measurement"),
        Matchers.equalTo(expected.measurement)
    );
    MatcherAssert.assertThat(
        "correct timestamp",
        parsed.get(InfluxParser.TIMESTAMP_KEY),
        Matchers.equalTo(expected.timestamp)
    );
    expected.kv.forEach((k, v) -> MatcherAssert.assertThat("correct field " + k, parsed.get(k), Matchers.equalTo(v)));
    parsed.remove("measurement");
    parsed.remove(InfluxParser.TIMESTAMP_KEY);
    MatcherAssert.assertThat("No extra keys in parsed data", parsed.keySet(), Matchers.equalTo(expected.kv.keySet()));
  }

}