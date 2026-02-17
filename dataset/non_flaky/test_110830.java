class DummyClass_110830 {
  @Test
  public void testParse() {
    var param =
        ArgumentUtil.parseArgument(new FakeParameter(), new String[] {"--require", "require"});
    Assertions.assertEquals("require", param.require);
  }

}