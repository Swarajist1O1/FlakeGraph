class DummyClass_162633 {
  @Test
  public void extractForwardedForIpv6() {
    assertEquals(
        "1111:1111:1111:1111:1111:1111:1111:1111",
        HttpServerTracer.extractForwardedFor("\"[1111:1111:1111:1111:1111:1111:1111:1111]\""));
  }

}