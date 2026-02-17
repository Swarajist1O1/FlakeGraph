class DummyClass_162642 {
  @Test
  public void extractForwardedForMultipleIpv6Unquoted() {
    assertEquals(
        "1111:1111:1111:1111:1111:1111:1111:1111",
        HttpServerTracer.extractForwardedFor("[1111:1111:1111:1111:1111:1111:1111:1111],1.2.3.4"));
  }

}