class DummyClass_60880 {
  @Test
  public void testParseFailures(Pair<String, String> testCase)
  {
    Parser<String, Object> parser = new InfluxParser(null);
    try {
      parser.parseToMap(testCase.rhs);
    }
    catch (ParseException t) {
      MatcherAssert.assertThat(t, Matchers.isA(ParseException.class));
      return;
    }

    Assert.fail(testCase.rhs + ": exception not thrown");
  }

}