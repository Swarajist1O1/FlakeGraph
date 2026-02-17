class DummyClass_156117 {
  @Test
  public void markerInterfaces() {
    String testClass = "soot.lambdaMetaFactory.MarkerInterfaces";

    final SootMethod target
        = prepareTarget(methodSigFromComponents(testClass, TEST_METHOD_RET, TEST_METHOD_NAME), testClass);

    final CallGraph cg = Scene.v().getCallGraph();

    final String metaFactoryClass = getMetaFactoryNameLambda(testClass, TEST_METHOD_NAME);

    final SootMethod bootstrap = Scene.v()
        .getMethod(methodSigFromComponents(metaFactoryClass, "java.util.function.Supplier", "bootstrap$", testClass));
    final SootMethod metaFactoryConstructor
        = Scene.v().getMethod(methodSigFromComponents(metaFactoryClass, "void", "<init>", testClass));
    final SootMethod get = Scene.v().getMethod(methodSigFromComponents(metaFactoryClass, "java.lang.Object", "get"));
    final SootMethod lambdaBody
        = Scene.v().getMethod(methodSigFromComponents(testClass, "java.lang.Object", "lambda$main$0"));
    final SootMethod getString = Scene.v().getMethod(methodSigFromComponents(testClass, "java.lang.String", "getString"));

    final List<Edge> edgesFromTarget = newArrayList(cg.edgesOutOf(target));

    assertTrue("There should be an edge from main to the bootstrap method of the synthetic LambdaMetaFactory",
        edgesFromTarget.stream().anyMatch(e -> e.tgt().equals(bootstrap) && e.isStatic()));
    assertTrue("There should be an edge to the constructor of the LambdaMetaFactory in the bootstrap method",
        newArrayList(cg.edgesOutOf(bootstrap)).stream()
            .anyMatch(e -> e.tgt().equals(metaFactoryConstructor) && e.isSpecial()));
    assertTrue(
        "There should be an interface invocation on the synthetic LambdaMetaFactory's implementation of the functional interface in the main method",
        edgesFromTarget.stream().anyMatch(e -> e.getTgt().equals(get) && e.kind() == Kind.INTERFACE));
    assertTrue(
        "There should be a virtual call to the lambda body implementation in the generated functional interface implementation of the synthetic LambdaMetaFactory",
        newArrayList(cg.edgesOutOf(get)).stream().anyMatch(e -> e.getTgt().equals(lambdaBody) && e.isVirtual()));

    assertTrue("There should be a virtual call to the getString method in actual lambda body implementation",
        newArrayList(cg.edgesOutOf(lambdaBody)).stream().anyMatch(e -> e.getTgt().equals(getString) && e.isVirtual()));

    validateAllBodies(target.getDeclaringClass(), bootstrap.getDeclaringClass());
  }

}