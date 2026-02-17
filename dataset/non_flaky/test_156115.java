class DummyClass_156115 {
  @Test
  public void lambdaNoCaptures() {
    String testClass = "soot.lambdaMetaFactory.LambdaNoCaptures";

    final SootMethod target = prepareTarget(methodSigFromComponents(testClass, TEST_METHOD_RET, TEST_METHOD_NAME), testClass,
        "java.util.function.Function");

    final CallGraph cg = Scene.v().getCallGraph();

    final String metaFactoryClass = getMetaFactoryNameLambda(testClass, TEST_METHOD_NAME);

    final SootMethod bootstrap
        = Scene.v().getMethod(methodSigFromComponents(metaFactoryClass, "java.util.function.Function", "bootstrap$"));
    final SootMethod metaFactoryConstructor
        = Scene.v().getMethod(methodSigFromComponents(metaFactoryClass, "void", "<init>"));
    final SootMethod apply
        = Scene.v().getMethod(methodSigFromComponents(metaFactoryClass, "java.lang.Object", "apply", "java.lang.Object"));
    final SootMethod lambdaBody
        = Scene.v().getMethod(methodSigFromComponents(testClass, "java.lang.String", "lambda$main$0", "java.lang.Integer"));
    final SootMethod staticCallee
        = Scene.v().getMethod(methodSigFromComponents(testClass, "void", "staticCallee", "java.lang.Integer"));

    final List<Edge> edgesFromTarget = newArrayList(cg.edgesOutOf(target));

    assertTrue("There should be an edge from main to the bootstrap method of the synthetic LambdaMetaFactory",
        edgesFromTarget.stream().anyMatch(e -> e.tgt().equals(bootstrap) && e.isStatic()));
    assertTrue("There should be an edge to the constructor of the LambdaMetaFactory in the bootstrap method",
        newArrayList(cg.edgesOutOf(bootstrap)).stream()
            .anyMatch(e -> e.tgt().equals(metaFactoryConstructor) && e.isSpecial()));
    assertTrue(
        "There should be an instance invocation on the synthetic LambdaMetaFactory's implementation of the functional interface in the main method",
        edgesFromTarget.stream().anyMatch(e -> e.getTgt().equals(apply) && e.kind() == Kind.INTERFACE));
    assertTrue(
        "There should be a static call to the lambda body implementation in the generated functional interface implementation of the synthetic LambdaMetaFactory",
        newArrayList(cg.edgesOutOf(apply)).stream().anyMatch(e -> e.getTgt().equals(lambdaBody) && e.isStatic()));

    assertTrue("There should be a static call to the staticCallee method in actual lambda body implementation",
        newArrayList(cg.edgesOutOf(lambdaBody)).stream().anyMatch(e -> e.getTgt().equals(staticCallee) && e.isStatic()));

    validateAllBodies(target.getDeclaringClass(), bootstrap.getDeclaringClass());
  }

}