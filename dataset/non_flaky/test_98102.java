class DummyClass_98102 {
  @Test
  public void testSocketSettings() {
    int connectTimeoutMS = Math.abs(TestUtils.randomInt());
    int socketTimeoutMS = Math.abs(TestUtils.randomInt());
    int receiveBufferSize = Math.abs(TestUtils.randomInt());
    int sendBufferSize = Math.abs(TestUtils.randomInt());

    JsonObject config = new JsonObject();
    config.put("connectTimeoutMS", connectTimeoutMS);
    config.put("socketTimeoutMS", socketTimeoutMS);
    config.put("receiveBufferSize", receiveBufferSize);
    config.put("sendBufferSize", sendBufferSize);

    SocketSettings settings = new SocketSettingsParser(null, config).settings();
    assertEquals(connectTimeoutMS, settings.getConnectTimeout(TimeUnit.MILLISECONDS));
    assertEquals(socketTimeoutMS, settings.getReadTimeout(TimeUnit.MILLISECONDS));
    assertEquals(receiveBufferSize, settings.getReceiveBufferSize());
    assertEquals(sendBufferSize, settings.getSendBufferSize());
  }

}