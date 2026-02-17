class DummyClass_162627 {
  @Test
  public void extractForwardedMultipleIpV6WithPort() {
    assertEquals(
        "1111:1111:1111:1111:1111:1111:1111:1111",
        HttpServerTracer.extractForwarded(
            "for=\"[1111:1111:1111:1111:1111:1111:1111:1111]:2222\";for=1.2.3.4"));
  }

}