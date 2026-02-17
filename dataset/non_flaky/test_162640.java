class DummyClass_162640 {
  @Test
  public void extractForwardedForMultiple() {
    assertEquals("1.1.1.1", HttpServerTracer.extractForwardedFor("1.1.1.1,1.2.3.4"));
  }

}