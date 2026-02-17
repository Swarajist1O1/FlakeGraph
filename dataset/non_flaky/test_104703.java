class DummyClass_104703 {
  @Test
  public void testSetInstanceIdToHostname()
      throws Exception {
    String expectedHost = NetUtils.getHostnameOrAddress();
    String expectedInstanceId = PREFIX_OF_SERVER_INSTANCE + expectedHost + "_" + DEFAULT_SERVER_NETTY_PORT;

    Map<String, Object> properties = new HashMap<>();
    properties.put(SET_INSTANCE_ID_TO_HOSTNAME_KEY, true);

    verifyInstanceConfig(new PinotConfiguration(properties), expectedInstanceId, expectedHost,
        DEFAULT_SERVER_NETTY_PORT);
  }

}