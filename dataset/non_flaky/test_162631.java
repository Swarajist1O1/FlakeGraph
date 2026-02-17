class DummyClass_162631 {
  @Test
  public void extractForwardedMixedSplitterIpv6WithPort() {
    assertEquals(
        "1111:1111:1111:1111:1111:1111:1111:1111",
        HttpServerTracer.extractForwarded(
            "test=abcd; by=1.2.3.4, for=\"[1111:1111:1111:1111:1111:1111:1111:1111]:2222\";for=1.2.3.4"));
  }

}