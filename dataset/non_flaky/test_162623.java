class DummyClass_162623 {
  @Test
  public void extractForwardedNoFor() {
    assertNull(HttpServerTracer.extractForwarded("by=1.1.1.1;test=1.1.1.1"));
  }

}