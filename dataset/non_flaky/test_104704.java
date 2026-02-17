class DummyClass_104704 {
  @Test
  public void testCustomInstanceId()
      throws Exception {
    Map<String, Object> properties = new HashMap<>();
    properties.put(CONFIG_OF_INSTANCE_ID, CUSTOM_INSTANCE_ID);

    verifyInstanceConfig(new PinotConfiguration(properties), CUSTOM_INSTANCE_ID, NetUtils.getHostAddress(),
        DEFAULT_SERVER_NETTY_PORT);
  }

}