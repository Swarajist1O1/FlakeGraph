class DummyClass_156108 {
  @Test
  public void superClassInterfaceWithSameSignatureTest() {
    String testClass = "soot.defaultInterfaceMethods.SuperClassInterfaceSameSignature";
    String defaultClass = "soot.defaultInterfaceMethods.PrintInterface";
    String defaultSuperClass = "soot.defaultInterfaceMethods.DefaultPrint";
    String superClassImplementsInterface = "soot.defaultInterfaceMethods.SuperClassImplementsInterface";

    final SootMethod target =
        prepareTarget(
            methodSigFromComponents(testClass, voidType, mainClass),
            testClass,
            defaultClass,
            superClassImplementsInterface);

    SootMethod defaultSuperMainMethod =
        Scene.v()
            .getMethod("<soot.defaultInterfaceMethods.SuperClassImplementsInterface: void main()>");
    SootMethod mainMethod =
        Scene.v()
            .getMethod(
                "<soot.defaultInterfaceMethods.SuperClassImplementsInterface: void print()>");
    SootMethod defaultMethod =
        Scene.v().getMethod("<soot.defaultInterfaceMethods.PrintInterface: void print()>");
    SootMethod defaultSuperClassMethod =
        Scene.v().getMethod("<soot.defaultInterfaceMethods.DefaultPrint: void print()>");

    Body mainBody = target.retrieveActiveBody();
    SootMethod refMainMethod = resolveMethodRefInBody(mainBody.getUnits(), "void print()");

    SootMethod resolvedMethod =
        VirtualCalls.v()
            .resolveNonSpecial(Scene.v().getRefType(testClass), defaultMethod.makeRef(), false);
    SootMethod resolvedSuperClassDefaultMethod =
        VirtualCalls.v()
            .resolveNonSpecial(
                Scene.v().getRefType(testClass), defaultSuperClassMethod.makeRef(), false);

    SootMethod concreteImpl =
        Scene.v()
            .getFastHierarchy()
            .resolveConcreteDispatch(Scene.v().getSootClass(testClass), defaultMethod);

    Set<SootMethod> abstractImpl =
        Scene.v()
            .getFastHierarchy()
            .resolveAbstractDispatch(Scene.v().getSootClass(defaultClass), defaultMethod);
    Set<SootMethod> abstractImplSuperClass =
        Scene.v()
            .getFastHierarchy()
            .resolveAbstractDispatch(
                Scene.v().getSootClass(defaultSuperClass), defaultSuperClassMethod);

    boolean edgeMainToSuperClassPrint = checkInEdges(mainMethod, target);
    boolean edgeMainToDefaultPrint = checkInEdges(defaultMethod, target);
    boolean edgeMainToSuperDefaultPrint = checkInEdges(defaultSuperClassMethod, target);
    boolean edgeSuperMainToSuperPrint =
        checkInEdges(defaultSuperClassMethod, defaultSuperMainMethod);

    final ReachableMethods reachableMethods = Scene.v().getReachableMethods();

    List<SootMethod> targetMethods =
        new ArrayList<SootMethod>() {
          {
            add(mainMethod);
            add(defaultMethod);
            add(defaultSuperClassMethod);
          }
        };

    ArrayList<Boolean> edgeNotPresent =
        new ArrayList<Boolean>() {
          {
            add(edgeMainToDefaultPrint);
            add(edgeMainToSuperDefaultPrint);
            add(edgeSuperMainToSuperPrint);
          }
        };

    Map<SootMethod, SootMethod> resolvedMethods =
        new HashMap<SootMethod, SootMethod>() {
          {
            put(mainMethod, resolvedMethod);
            put(resolvedSuperClassDefaultMethod, resolvedMethod);
          }
        };

    for (SootMethod targetMethod : targetMethods) {
      assertNotNull(targetMethod);
    }
    assertEquals(targetMethods.get(0), refMainMethod);
    assertEquals(targetMethods.get(0).getName(), "print");
    assertTrue(edgeMainToSuperClassPrint);
    for (boolean notPresent : edgeNotPresent) {
      assertFalse(notPresent);
    }
    assertEquals(targetMethods.get(0), concreteImpl);
    assertNotEquals(targetMethods.get(1), concreteImpl);
    assertTrue(
        abstractImpl.contains(
            Scene.v()
                .getMethod(
                    "<soot.defaultInterfaceMethods.SuperClassImplementsInterface: void print()>")));
    assertTrue(
        abstractImplSuperClass.contains(
            Scene.v()
                .getMethod(
                    "<soot.defaultInterfaceMethods.SuperClassImplementsInterface: void print()>")));
  }

}