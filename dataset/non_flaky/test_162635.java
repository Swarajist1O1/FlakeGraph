class DummyClass_162635 {
  @Test
  public void extractForwardedForIpv6Unbracketed() {
    assertEquals(
        "1111:1111:1111:1111:1111:1111:1111:1111",
        HttpServerTracer.extractForwardedFor("1111:1111:1111:1111:1111:1111:1111:1111"));
  }

}