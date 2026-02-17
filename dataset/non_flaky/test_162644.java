class DummyClass_162644 {
  @Test
  public void extractForwardedForMultipleWithPort() {
    assertEquals("1.1.1.1", HttpServerTracer.extractForwardedFor("1.1.1.1:2222,1.2.3.4"));
  }

}