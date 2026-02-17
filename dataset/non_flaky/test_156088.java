class DummyClass_156088 {
  @Test
  public void anySubTypePointsToResolution() {
    SootMethod entryPoint = prepareTarget(TEST_PTA_ENTRY_POINT, TEST_PACKAGE);
    commonInvokeTest(entryPoint);
  }

}