class DummyClass_104706 {
  @Test
  public void testCustomPort()
      throws Exception {
    String expectedHost = NetUtils.getHostAddress();
    String expectedInstanceId = PREFIX_OF_SERVER_INSTANCE + expectedHost + "_" + CUSTOM_PORT;

    Map<String, Object> properties = new HashMap<>();
    properties.put(KEY_OF_SERVER_NETTY_PORT, CUSTOM_PORT);

    verifyInstanceConfig(new PinotConfiguration(properties), expectedInstanceId, expectedHost, CUSTOM_PORT);
  }

}