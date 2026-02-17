class DummyClass_162636 {
  @Test
  public void extractForwardedForWithPort() {
    assertEquals("1.1.1.1", HttpServerTracer.extractForwardedFor("1.1.1.1:2222"));
  }

}