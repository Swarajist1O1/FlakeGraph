class DummyClass_60879 {
  @Test
  public void testParseWhitelistFail()
  {
    Parser<String, Object> parser = new InfluxParser(Sets.newHashSet("mem"));
    String input = "cpu,host=foo.bar.baz,region=us-east,application=echo pct_idle=99.3,pct_user=88.8,m1_load=2 1465839830100400200";
    try {
      parser.parseToMap(input);
    }
    catch (ParseException t) {
      MatcherAssert.assertThat(t, Matchers.isA(ParseException.class));
      return;
    }

    Assert.fail("Exception not thrown");
  }

}