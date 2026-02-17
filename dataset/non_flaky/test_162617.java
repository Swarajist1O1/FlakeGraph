class DummyClass_162617 {
  @Test
  public void extractForwardedIpv6WithPort() {
    assertEquals(
        "1111:1111:1111:1111:1111:1111:1111:1111",
        HttpServerTracer.extractForwarded(
            "for=\"[1111:1111:1111:1111:1111:1111:1111:1111]:2222\""));
  }

}