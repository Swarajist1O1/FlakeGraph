class DummyClass_156127 {
  @Test
  public void getVertrag2Test() {
    String testClass = "soot.lambdaMetaFactory.Issue1146";

    final SootMethod target = prepareTarget(
        methodSigFromComponents(testClass, "soot.lambdaMetaFactory.Issue1146$Vertrag", "getVertrag2", "java.lang.String"),
        testClass, "java.util.function.Function");
    // if no exception is thrown, everything is working as intended
  }

}