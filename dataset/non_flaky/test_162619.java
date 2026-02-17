class DummyClass_162619 {
  @Test
  public void extractForwardedMalformed() {
    assertNull(HttpServerTracer.extractForwarded("for=;for=1.1.1.1"));
  }

}