class DummyClass_156081 {
  @Test
  public void findsTarget() {
    final SootMethod sootMethod = prepareTarget("<" + TEST_TARGET_CLASS + ": void helloWorld()>", TEST_TARGET_CLASS);
    Assert.assertNotNull("Could not find target method. System test setup seems to be incorrect.", sootMethod);
    Assert.assertTrue(sootMethod.isConcrete());
    Assert.assertNotNull(sootMethod.retrieveActiveBody());
  }

}