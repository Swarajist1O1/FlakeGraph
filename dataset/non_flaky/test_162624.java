class DummyClass_162624 {
  @Test
  public void extractForwardedMultiple() {
    assertEquals("1.1.1.1", HttpServerTracer.extractForwarded("for=1.1.1.1;for=1.2.3.4"));
  }

}