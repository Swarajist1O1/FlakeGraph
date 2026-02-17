class DummyClass_162616 {
  @Test
  public void extractForwardedWithPort() {
    assertEquals("1.1.1.1", HttpServerTracer.extractForwarded("for=\"1.1.1.1:2222\""));
  }

}