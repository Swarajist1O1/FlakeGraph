class DummyClass_162614 {
  @Test
  public void extractForwarded() {
    assertEquals("1.1.1.1", HttpServerTracer.extractForwarded("for=1.1.1.1"));
  }

}