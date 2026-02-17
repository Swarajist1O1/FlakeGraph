class DummyClass_156129 {
  @Test
  public void parameterWidening() {
    String testClass = "soot.lambdaMetaFactory.Adapt";

    final SootMethod target = prepareTarget(methodSigFromComponents(testClass, "void", "parameterWidening"), testClass);

    // TODO more fine-grained testing

    validateAllBodies(target.getDeclaringClass());
  }

}