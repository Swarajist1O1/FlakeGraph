class DummyClass_162609 {
  @Test
  public void extensionsAreLoadedFromJar() throws IOException, InterruptedException {
    startTarget("/opentelemetry-extensions.jar");

    testAndVerify();

    stopTarget();
  }

}