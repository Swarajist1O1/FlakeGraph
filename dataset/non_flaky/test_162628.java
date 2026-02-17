class DummyClass_162628 {
  @Test
  public void extractForwardedMixedSplitter() {
    assertEquals(
        "1.1.1.1",
        HttpServerTracer.extractForwarded("test=abcd; by=1.2.3.4, for=1.1.1.1;for=1.2.3.4"));
  }

}