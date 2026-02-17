class DummyClass_156131 {
  @Test
  public void returnWidening() {
    String testClass = "soot.lambdaMetaFactory.Adapt";

    final SootMethod target = prepareTarget(methodSigFromComponents(testClass, "void", "returnWidening"), testClass);

    // TODO more fine-grained testing

    validateAllBodies(target.getDeclaringClass());
  }

}