class DummyClass_110835 {
  @Test
  public void testSetConverter() {
    FakeParameter param =
        ArgumentUtil.parseArgument(
            new FakeParameter(),
            new String[] {"--require", "require", "--setConverter", "1", "1", "2"});

    Assertions.assertEquals(Set.of("1", "2"), param.setConverter);
  }

}