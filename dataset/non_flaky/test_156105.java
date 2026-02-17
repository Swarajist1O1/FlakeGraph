class DummyClass_156105 {
  @Test
  public void simpleDefaultInterfaceTest() {

    String testClass = "soot.defaultInterfaceMethods.SimpleDefaultInterface";
    String defaultClass = "soot.defaultInterfaceMethods.Default";
    String classToAnalyze = "soot.defaultInterfaceMethods.Default";

    final SootMethod target =
        prepareTarget(
            methodSigFromComponents(testClass, voidType, mainClass),
            testClass,
            classToAnalyze);

    SootMethod defaultMethod =
        Scene.v().getMethod("<soot.defaultInterfaceMethods.Default: void target()>");
    Body body = target.retrieveActiveBody();
    SootMethod targetMethod = resolveMethodRefInBody(body.getUnits(), "void target()");
    SootMethod resolvedMethod =
        VirtualCalls.v()
            .resolveNonSpecial(Scene.v().getRefType(testClass), defaultMethod.makeRef(), false);
    SootMethod concreteImpl =
        Scene.v()
            .getFastHierarchy()
            .resolveConcreteDispatch(Scene.v().getSootClass(testClass), defaultMethod);
    SootMethod concreteImplViaResolveMethod =
        Scene.v()
            .getFastHierarchy()
            .resolveMethod(Scene.v().getSootClass(testClass), defaultMethod, false);
    Set<SootMethod> abstractImpl =
        Scene.v()
            .getFastHierarchy()
            .resolveAbstractDispatch(Scene.v().getSootClass(defaultClass), defaultMethod);

    boolean edgePresent = checkInEdges(defaultMethod, target);
    final ReachableMethods reachableMethods = Scene.v().getReachableMethods();
    /* Arguments for assert function */

    assertEquals(defaultMethod, resolvedMethod);
    assertEquals(defaultMethod, targetMethod);
    assertEquals(defaultMethod.getName(), "target");
    assertNotNull(defaultMethod);
    assertTrue(reachableMethods.contains(defaultMethod));
    assertTrue(edgePresent);
    assertEquals(defaultMethod, concreteImpl);
    assertEquals(concreteImpl, concreteImplViaResolveMethod);
    assertTrue(
        abstractImpl.contains(
            Scene.v().getMethod("<soot.defaultInterfaceMethods.Default: void target()>")));
  }

}