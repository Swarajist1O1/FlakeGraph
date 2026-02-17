class DummyClass_162629 {
  @Test
  public void extractForwardedMixedSplitterIpv6() {
    assertEquals(
        "1111:1111:1111:1111:1111:1111:1111:1111",
        HttpServerTracer.extractForwarded(
            "test=abcd; by=1.2.3.4, for=\"[1111:1111:1111:1111:1111:1111:1111:1111]\";for=1.2.3.4"));
  }

}