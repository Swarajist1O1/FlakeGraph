class DummyClass_162615 {
  @Test
  public void extractForwardedIpv6() {
    assertEquals(
        "1111:1111:1111:1111:1111:1111:1111:1111",
        HttpServerTracer.extractForwarded("for=\"[1111:1111:1111:1111:1111:1111:1111:1111]\""));
  }

}