class DummyClass_104707 {
  @Test
  public void testAllCustomServerConf()
      throws Exception {
    Map<String, Object> properties = new HashMap<>();
    properties.put(CONFIG_OF_INSTANCE_ID, CUSTOM_INSTANCE_ID);
    properties.put(KEY_OF_SERVER_NETTY_HOST, CUSTOM_HOST);
    properties.put(KEY_OF_SERVER_NETTY_PORT, CUSTOM_PORT);
    verifyInstanceConfig(new PinotConfiguration(properties), CUSTOM_INSTANCE_ID, CUSTOM_HOST, CUSTOM_PORT);
  }

}