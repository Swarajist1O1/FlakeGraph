class DummyClass_156107 {
  @Test
  public void classInterfaceWithSameSignatureTest() {
    String testClass = "soot.defaultInterfaceMethods.ClassInterfaceSameSignature";
    String defaultClass = "soot.defaultInterfaceMethods.HelloWorld";

    final SootMethod target =
        prepareTarget(
            methodSigFromComponents(testClass, voidType, mainClass),
            testClass,
            defaultClass);

    SootMethod mainPrintMethod =
        Scene.v()
            .getMethod("<soot.defaultInterfaceMethods.ClassInterfaceSameSignature: void print()>");
    SootMethod defaultPrintMethod =
        Scene.v().getMethod("<soot.defaultInterfaceMethods.HelloWorld: void print()>");

    Body mainBody = target.retrieveActiveBody();
    SootMethod refMainMethod = resolveMethodRefInBody(mainBody.getUnits(), "void print()");
    SootMethod resolvedMethod =
        VirtualCalls.v()
            .resolveNonSpecial(
                Scene.v().getRefType(testClass), defaultPrintMethod.makeRef(), false);
    SootMethod concreteImpl =
        Scene.v()
            .getFastHierarchy()
            .resolveConcreteDispatch(Scene.v().getSootClass(testClass), defaultPrintMethod);
    Set<SootMethod> abstractImpl =
        Scene.v()
            .getFastHierarchy()
            .resolveAbstractDispatch(Scene.v().getSootClass(defaultClass), defaultPrintMethod);
    boolean edgeMainMethodToMainPrint = checkInEdges(mainPrintMethod, target);
    boolean edgeMainPrintToDefaultPrint = checkInEdges(defaultPrintMethod, target);
    final ReachableMethods reachableMethods = Scene.v().getReachableMethods();

    Map<SootMethod, String> targetMethods =
        new HashMap<SootMethod, String>() {
          {
            put(mainPrintMethod, "print");
            put(defaultPrintMethod, "print");
          }
        };

    ArrayList<Boolean> edgePresent =
        new ArrayList<Boolean>() {
          {
            add(edgeMainMethodToMainPrint);
          }
        };

    for (Map.Entry<SootMethod, String> targetMethod : targetMethods.entrySet()) {
      assertNotNull(targetMethod.getKey());
    }
    assertEquals(mainPrintMethod, resolvedMethod);
    assertEquals(mainPrintMethod, refMainMethod);
    for (Map.Entry<SootMethod, String> targetMethod : targetMethods.entrySet()) {
      assertEquals(targetMethod.getKey().getName(), targetMethod.getValue());
    }

    assertTrue(reachableMethods.contains(mainPrintMethod));

    for (boolean isPresent : edgePresent) {
      assertTrue(isPresent);
    }
    assertEquals(mainPrintMethod, concreteImpl);
    assertTrue(
        abstractImpl.contains(
            Scene.v()
                .getMethod(
                    "<soot.defaultInterfaceMethods.ClassInterfaceSameSignature: void print()>")));
  }

}