class DummyClass_110833 {
  @Test
  public void testNotNegative() {
    FakeParameter param =
        ArgumentUtil.parseArgument(
            new FakeParameter(),
            new String[] {"--require", "require", "--longNotNegative", "1000"});

    Assertions.assertEquals(1000, param.longNotNegative);
    Assertions.assertThrows(
        ParameterException.class,
        () ->
            ArgumentUtil.parseArgument(
                new FakeParameter(),
                new String[] {"--require", "require", "--longNotNegative", "-1"}));
  }

}