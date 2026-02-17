class DummyClass_162645 {
  @Test
  public void extractForwardedForMultipleIpv6WithPort() {
    assertEquals(
        "1111:1111:1111:1111:1111:1111:1111:1111",
        HttpServerTracer.extractForwardedFor(
            "\"[1111:1111:1111:1111:1111:1111:1111:1111]:2222\",1.2.3.4"));
  }

}