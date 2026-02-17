class DummyClass_162625 {
  @Test
  public void extractForwardedMultipleIpV6() {
    assertEquals(
        "1111:1111:1111:1111:1111:1111:1111:1111",
        HttpServerTracer.extractForwarded(
            "for=\"[1111:1111:1111:1111:1111:1111:1111:1111]\";for=1.2.3.4"));
  }

}