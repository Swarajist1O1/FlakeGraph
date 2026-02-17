class DummyClass_162611 {
  @Test
  public void extensionsAreLoadedFromJavaagent() throws IOException, InterruptedException {
    startTargetWithExtendedAgent();

    testAndVerify();

    stopTarget();
  }

}