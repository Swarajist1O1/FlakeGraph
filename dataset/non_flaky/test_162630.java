class DummyClass_162630 {
  @Test
  public void extractForwardedMixedSplitterWithPort() {
    assertEquals(
        "1.1.1.1",
        HttpServerTracer.extractForwarded(
            "test=abcd; by=1.2.3.4, for=\"1.1.1.1:2222\";for=1.2.3.4"));
  }

}