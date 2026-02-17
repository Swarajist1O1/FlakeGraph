class DummyClass_162643 {
  @Test
  public void extractForwardedForMultipleIpv6Unbracketed() {
    assertEquals(
        "1111:1111:1111:1111:1111:1111:1111:1111",
        HttpServerTracer.extractForwardedFor("1111:1111:1111:1111:1111:1111:1111:1111,1.2.3.4"));
  }

}