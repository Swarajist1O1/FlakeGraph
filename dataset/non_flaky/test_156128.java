class DummyClass_156128 {
  @Test
  public void parameterBoxing() {
    String testClass = "soot.lambdaMetaFactory.Adapt";

    final SootMethod target = prepareTarget(methodSigFromComponents(testClass, "void", "parameterBoxingTarget"), testClass);

    // TODO more fine-grained testing

    validateAllBodies(target.getDeclaringClass());
  }

}