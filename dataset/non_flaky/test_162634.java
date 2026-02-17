class DummyClass_162634 {
  @Test
  public void extractForwardedForIpv6Unquoted() {
    assertEquals(
        "1111:1111:1111:1111:1111:1111:1111:1111",
        HttpServerTracer.extractForwardedFor("[1111:1111:1111:1111:1111:1111:1111:1111]"));
  }

}