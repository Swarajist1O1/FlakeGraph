class DummyClass_156110 {
  @Test
  public void interfaceInheritanceTest() {
    String testClass = "soot.defaultInterfaceMethods.InterfaceInheritance";
    String defaultClass = "soot.defaultInterfaceMethods.InterfaceTestA";
    String defaultInterface = "soot.defaultInterfaceMethods.InterfaceTestB";

    final SootMethod target =
        prepareTarget(
            methodSigFromComponents(testClass, voidType, mainClass),
            testClass,
            defaultClass,
            defaultInterface);

    SootMethod interfaceTestAPrint =
        Scene.v().getMethod("<soot.defaultInterfaceMethods.InterfaceTestA: void print()>");
    SootMethod mainPrintMessageMethod =
        Scene.v()
            .getMethod("<soot.defaultInterfaceMethods.InterfaceInheritance: void printMessage()>");
    Body mainBody = target.retrieveActiveBody();
    SootMethod refMainMethod = resolveMethodRefInBody(mainBody.getUnits(), "void print()");
    SootMethod resolvedMethod =
        VirtualCalls.v()
            .resolveNonSpecial(
                Scene.v().getRefType(testClass), interfaceTestAPrint.makeRef(), false);
    SootMethod concreteImpl =
        Scene.v()
            .getFastHierarchy()
            .resolveConcreteDispatch(Scene.v().getSootClass(testClass), interfaceTestAPrint);
    Set<SootMethod> abstractImpl =
        Scene.v()
            .getFastHierarchy()
            .resolveAbstractDispatch(Scene.v().getSootClass(defaultClass), interfaceTestAPrint);

    boolean edgeMainToInterfaceTestAPrint = checkInEdges(interfaceTestAPrint, target);
    boolean edgeMainToMainPrintMessage = checkInEdges(mainPrintMessageMethod, target);
    final ReachableMethods reachableMethods = Scene.v().getReachableMethods();

    List<SootMethod> targetMethods =
        new ArrayList<SootMethod>() {
          {
            add(interfaceTestAPrint);
            add(mainPrintMessageMethod);
          }
        };

    for (SootMethod targetMethod : targetMethods) {
      Assert.assertNotNull(targetMethod);
    }
    assertEquals(targetMethods.get(0), refMainMethod);
    assertEquals(targetMethods.get(0).getName(), "print");
    assertTrue(edgeMainToInterfaceTestAPrint);
    assertFalse(edgeMainToMainPrintMessage);
    assertTrue(reachableMethods.contains(targetMethods.get(0)));
    assertFalse(reachableMethods.contains(targetMethods.get(1)));
    assertEquals(targetMethods.get(0), resolvedMethod);
    assertEquals(targetMethods.get(0), concreteImpl);
    assertTrue(abstractImpl.contains(targetMethods.get(0)));
  }

}