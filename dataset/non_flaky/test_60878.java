class DummyClass_60878 {
  @Test
  public void testParseWhitelistPass()
  {
    Parser<String, Object> parser = new InfluxParser(Sets.newHashSet("cpu"));
    String input = "cpu,host=foo.bar.baz,region=us-east,application=echo pct_idle=99.3,pct_user=88.8,m1_load=2 1465839830100400200";
    Map<String, Object> parsed = parser.parseToMap(input);
    MatcherAssert.assertThat(parsed.get("measurement"), Matchers.equalTo("cpu"));
  }

}