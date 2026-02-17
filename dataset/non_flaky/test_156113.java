class DummyClass_156113 {
  @Test
  public void maximallySpecificSuperInterface() {
    String targetClassName = "soot.defaultInterfaceMethods.MaximallySpecificSuperInterface";
    String superClass = "soot.defaultInterfaceMethods.B";
    String subInterface = "soot.defaultInterfaceMethods.C";
    String superInterface = "soot.defaultInterfaceMethods.D";

    final SootMethod mainMethod =
        prepareTarget(
            methodSigFromComponents(targetClassName, voidType, mainClass),
            targetClassName,
            superClass,
            subInterface,
            superInterface);

    SootClass testClass = mainMethod.getDeclaringClass();

    SootMethod subInterfacePrint =
        Scene.v().getMethod(methodSigFromComponents(subInterface, "void print()"));
    SootMethod superInterfacePrint =
        Scene.v().getMethod(methodSigFromComponents(superInterface, "void print()"));

    SootMethod methodRefResolved =
        resolveMethodRefInBody(mainMethod.retrieveActiveBody().getUnits(), "void print()");
    assertEquals(subInterfacePrint, methodRefResolved);

    SootMethod virtualCallsResolved =
        VirtualCalls.v()
            .resolveNonSpecial(testClass.getType(), superInterfacePrint.makeRef(), false);
    assertEquals(subInterfacePrint, virtualCallsResolved);

    SootMethod concreteImplI1 =
        Scene.v().getFastHierarchy().resolveConcreteDispatch(testClass, superInterfacePrint);
    assertEquals(subInterfacePrint, concreteImplI1);

    Set<SootMethod> abstractImpl =
        Scene.v()
            .getFastHierarchy()
            .resolveAbstractDispatch(superInterfacePrint.getDeclaringClass(), superInterfacePrint);
    assertEquals(Sets.newHashSet(subInterfacePrint), abstractImpl);

    assertTrue(checkInEdges(subInterfacePrint, mainMethod));
    assertTrue(Scene.v().getReachableMethods().contains(subInterfacePrint));
  }

}