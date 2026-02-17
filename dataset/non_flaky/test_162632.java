class DummyClass_162632 {
  @Test
  public void extractForwardedFor() {
    assertEquals("1.1.1.1", HttpServerTracer.extractForwardedFor("1.1.1.1"));
  }

}