class DummyClass_156122 {
  @Test
  public void inheritedMethodRef() {
    String testClass = "soot.lambdaMetaFactory.InheritedMethodRef";

    final SootMethod target
        = prepareTarget(methodSigFromComponents(testClass, TEST_METHOD_RET, TEST_METHOD_NAME), testClass);

    final CallGraph cg = Scene.v().getCallGraph();

    final String referencedMethodName = "superMethod";

    final String metaFactoryClass = getMetaFactoryNameLambda(testClass, TEST_METHOD_NAME);

    final SootMethod bootstrap = Scene.v()
        .getMethod(methodSigFromComponents(metaFactoryClass, "java.util.function.Supplier", "bootstrap$", testClass));
    final SootMethod metaFactoryConstructor
        = Scene.v().getMethod(methodSigFromComponents(metaFactoryClass, "void", "<init>", testClass));
    final SootMethod get = Scene.v().getMethod(methodSigFromComponents(metaFactoryClass, "java.lang.Object", "get"));
    final SootMethod referencedMethod
        = Scene.v().getMethod(methodSigFromComponents("soot.lambdaMetaFactory.Super", "int", referencedMethodName));
    final SootMethod lambdaBody
        = Scene.v().getMethod(methodSigFromComponents(testClass, "java.lang.Integer", "lambda$main$0"));

    final List<Edge> edgesFromTarget = newArrayList(cg.edgesOutOf(target));

    assertTrue("There should be an edge from main to the bootstrap method of the synthetic LambdaMetaFactory",
        edgesFromTarget.stream().anyMatch(e -> e.tgt().equals(bootstrap) && e.isStatic()));
    assertTrue("There should be an edge to the constructor of the LambdaMetaFactory in the bootstrap method",
        newArrayList(cg.edgesOutOf(bootstrap)).stream()
            .anyMatch(e -> e.tgt().equals(metaFactoryConstructor) && e.isSpecial()));
    assertTrue(
        "There should be an interface invocation on the synthetic LambdaMetaFactory's implementation of the functional interface in the main method",
        edgesFromTarget.stream().anyMatch(e -> e.getTgt().equals(get) && e.kind() == Kind.INTERFACE));
    //Call is from <soot.lambdaMetaFactory.InheritedMethodRef$lambda_main_0__1
    //to           <soot.lambdaMetaFactory.InheritedMethodRef: java.lang.Integer lambda$main$0()>
    //As such, it needs to be a virtual call.
    assertTrue(
        "There should be a virtual call to the lambda body implementation in the generated functional interface implementation of the synthetic LambdaMetaFactory",
        newArrayList(cg.edgesOutOf(get)).stream().anyMatch(e -> e.getTgt().equals(lambdaBody) && e.isVirtual()));
    assertTrue("There should be a special call to the referenced method", newArrayList(cg.edgesOutOf(lambdaBody)).stream()
        .anyMatch(e -> e.getTgt().equals(referencedMethod) && e.isSpecial()));

    validateAllBodies(target.getDeclaringClass(), bootstrap.getDeclaringClass());
  }

}