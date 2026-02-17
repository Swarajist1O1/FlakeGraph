class DummyClass_162618 {
  @Test
  public void extractForwardedCaps() {
    assertEquals("1.1.1.1", HttpServerTracer.extractForwarded("For=1.1.1.1"));
  }

}