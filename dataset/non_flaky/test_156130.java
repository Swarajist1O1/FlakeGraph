class DummyClass_156130 {
  @Test
  public void returnBoxing() {
    String testClass = "soot.lambdaMetaFactory.Adapt";

    final SootMethod target = prepareTarget(methodSigFromComponents(testClass, "void", "returnBoxing"), testClass);

    // TODO more fine-grained testing

    validateAllBodies(target.getDeclaringClass());
  }

}