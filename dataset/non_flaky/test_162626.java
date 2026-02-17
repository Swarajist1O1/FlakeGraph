class DummyClass_162626 {
  @Test
  public void extractForwardedMultipleWithPort() {
    assertEquals("1.1.1.1", HttpServerTracer.extractForwarded("for=\"1.1.1.1:2222\";for=1.2.3.4"));
  }

}