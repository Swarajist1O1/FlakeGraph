class DummyClass_162621 {
  @Test
  public void extractForwardedEmptyValue() {
    assertNull(HttpServerTracer.extractForwarded("for="));
  }

}