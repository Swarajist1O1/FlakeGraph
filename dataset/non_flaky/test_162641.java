class DummyClass_162641 {
  @Test
  public void extractForwardedForMultipleIpv6() {
    assertEquals(
        "1111:1111:1111:1111:1111:1111:1111:1111",
        HttpServerTracer.extractForwardedFor(
            "\"[1111:1111:1111:1111:1111:1111:1111:1111]\",1.2.3.4"));
  }

}