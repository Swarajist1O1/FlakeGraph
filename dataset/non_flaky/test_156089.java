class DummyClass_156089 {
  @Test
  public void anySubTypeTypestateResolution() {
    SootMethod entryPoint = prepareTarget(TEST_TYPESTATE_ENTRY_POINT, TEST_PACKAGE);
    commonInvokeTest(entryPoint);
  }

}