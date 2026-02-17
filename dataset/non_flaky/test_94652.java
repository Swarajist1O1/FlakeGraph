class DummyClass_94652 {
  @Test
  public void findsAlpnMethods() {
    assumeTrue(getPlatform().equals("jdk9"));

    Jdk9Platform platform = Jdk9Platform.buildIfSupported();

    assertEquals("getApplicationProtocol", platform.getProtocolMethod.getName());
    assertEquals("setApplicationProtocols", platform.setProtocolMethod.getName());
  }

}