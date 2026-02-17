class DummyClass_156125 {
  @Test
  public void testNewTest() {
    String testClass = "soot.lambdaMetaFactory.Issue1292";
    prepareTarget(
        methodSigFromComponents(testClass, "void", "testNew", "java.util.List"),
        testClass,
        "java.util.function.Function");
    // if no exception is thrown, everything is working as intended
  }

}