class DummyClass_156118 {
  @Test
  public void staticMethodRef() {
    String testClass = "soot.lambdaMetaFactory.StaticMethodRef";

    final SootMethod target
        = prepareTarget(methodSigFromComponents(testClass, TEST_METHOD_RET, TEST_METHOD_NAME), testClass);

    final CallGraph cg = Scene.v().getCallGraph();

    final String referencedMethodName = "staticMethod";

    final String metaFactoryClass = getMetaFactoryNameMethodRef(testClass, referencedMethodName);

    final SootMethod bootstrap
        = Scene.v().getMethod(methodSigFromComponents(metaFactoryClass, "java.util.function.Supplier", "bootstrap$"));
    final SootMethod metaFactoryConstructor
        = Scene.v().getMethod(methodSigFromComponents(metaFactoryClass, "void", "<init>"));
    final SootMethod get = Scene.v().getMethod(methodSigFromComponents(metaFactoryClass, "java.lang.Object", "get"));
    final SootMethod referencedMethod = Scene.v().getMethod(methodSigFromComponents(testClass, "int", referencedMethodName));

    final List<Edge> edgesFromTarget = newArrayList(cg.edgesOutOf(target));

    assertTrue("There should be an edge from main to the bootstrap method of the synthetic LambdaMetaFactory",
        edgesFromTarget.stream().anyMatch(e -> e.tgt().equals(bootstrap) && e.isStatic()));
    assertTrue("There should be an edge to the constructor of the LambdaMetaFactory in the bootstrap method",
        newArrayList(cg.edgesOutOf(bootstrap)).stream()
            .anyMatch(e -> e.tgt().equals(metaFactoryConstructor) && e.isSpecial()));
    assertTrue(
        "There should be an interface invocation on the synthetic LambdaMetaFactory's implementation of the functional interface in the main method",
        edgesFromTarget.stream().anyMatch(e -> e.getTgt().equals(get) && e.kind() == Kind.INTERFACE));
    assertTrue("There should be a static call to the referenced method",
        newArrayList(cg.edgesOutOf(get)).stream().anyMatch(e -> e.getTgt().equals(referencedMethod) && e.isStatic()));

    validateAllBodies(target.getDeclaringClass(), bootstrap.getDeclaringClass());
  }

}