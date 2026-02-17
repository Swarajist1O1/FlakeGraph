class DummyClass_156126 {
  @Test
  public void getVertragTest() {
    String testClass = "soot.lambdaMetaFactory.Issue1146";

    final SootMethod target = prepareTarget(
        methodSigFromComponents(testClass, "soot.lambdaMetaFactory.Issue1146$Vertrag", "getVertrag", "java.lang.String"),
        testClass, "java.util.function.Function");
    // if no exception is thrown, everything is working as intended
  }

}