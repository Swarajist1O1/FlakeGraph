class DummyClass_162622 {
  @Test
  public void extractForwardedEmptyValueWithSemicolon() {
    assertNull(HttpServerTracer.extractForwarded("for=;"));
  }

}