class DummyClass_104705 {
  @Test
  public void testCustomHost()
      throws Exception {
    String expectedInstanceId = PREFIX_OF_SERVER_INSTANCE + CUSTOM_HOST + "_" + DEFAULT_SERVER_NETTY_PORT;

    Map<String, Object> properties = new HashMap<>();
    properties.put(KEY_OF_SERVER_NETTY_HOST, CUSTOM_HOST);

    verifyInstanceConfig(new PinotConfiguration(properties), expectedInstanceId, CUSTOM_HOST,
        DEFAULT_SERVER_NETTY_PORT);
  }

}