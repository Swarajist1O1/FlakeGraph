class DummyClass_162637 {
  @Test
  public void extractForwardedForIpv6WithPort() {
    assertEquals(
        "1111:1111:1111:1111:1111:1111:1111:1111",
        HttpServerTracer.extractForwardedFor("\"[1111:1111:1111:1111:1111:1111:1111:1111]:2222\""));
  }

}