class DummyClass_110834 {
  @Test
  public void testDurationConvert() {
    FakeParameter param =
        ArgumentUtil.parseArgument(
            new FakeParameter(),
            new String[] {"--require", "require", "--durationConvert", "1000"});

    Assertions.assertEquals(Duration.ofSeconds(1000), param.durationConvert);
  }

}