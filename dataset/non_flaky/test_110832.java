class DummyClass_110832 {
  @Test
  public void testLongPositive() {
    var param =
        ArgumentUtil.parseArgument(
            new FakeParameter(), new String[] {"--require", "require", "--longPositive", "1000"});

    Assertions.assertEquals(1000, param.longPositive);
    Assertions.assertThrows(
        ParameterException.class,
        () ->
            ArgumentUtil.parseArgument(
                new FakeParameter(), new String[] {"--require", "require", "--longPositive", "0"}));
  }

}