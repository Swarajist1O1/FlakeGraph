class DummyClass_162638 {
  @Test
  public void extractForwardedForIpv6UnquotedWithPort() {
    assertEquals(
        "1111:1111:1111:1111:1111:1111:1111:1111",
        HttpServerTracer.extractForwardedFor("[1111:1111:1111:1111:1111:1111:1111:1111]:2222"));
  }

}