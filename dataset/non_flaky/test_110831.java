class DummyClass_110831 {
  @Test
  public void testRequired() {
    Assertions.assertThrows(
        ParameterException.class,
        () -> ArgumentUtil.parseArgument(new FakeParameter(), new String[] {}));
  }

}