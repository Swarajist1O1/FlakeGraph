class DummyClass_104702 {
  @Test
  public void testDefaultServerConf()
      throws Exception {
    String expectedHost = NetUtils.getHostAddress();
    String expectedInstanceId = PREFIX_OF_SERVER_INSTANCE + expectedHost + "_" + DEFAULT_SERVER_NETTY_PORT;

    verifyInstanceConfig(new PinotConfiguration(), expectedInstanceId, expectedHost, DEFAULT_SERVER_NETTY_PORT);
  }

}