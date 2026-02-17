class DummyClass_162639 {
  @Test
  public void extractForwardedForEmpty() {
    assertNull(HttpServerTracer.extractForwardedFor(""));
  }

}