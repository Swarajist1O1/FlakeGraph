class DummyClass_162610 {
  @Test
  public void extensionsAreLoadedFromFolder() throws IOException, InterruptedException {
    startTarget("/");

    testAndVerify();

    stopTarget();
  }

}