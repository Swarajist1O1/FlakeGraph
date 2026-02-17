class DummyClass_162620 {
  @Test
  public void extractForwardedEmpty() {
    assertNull(HttpServerTracer.extractForwarded(""));
  }

}