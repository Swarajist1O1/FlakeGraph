class DummyClass_156111 {
  @Test
  public void interfaceReAbstractionTest() {
    String testClass = "soot.defaultInterfaceMethods.InterfaceReAbstracting";
    String defaultClass = "soot.defaultInterfaceMethods.InterfaceA";
    String defaultInterface = "soot.defaultInterfaceMethods.InterfaceB";

    final SootMethod target =
        prepareTarget(
            methodSigFromComponents(testClass, "void", "main"),
            testClass,
            defaultClass,
            defaultInterface);

    SootMethod interfaceAPrint =
        Scene.v().getMethod("<soot.defaultInterfaceMethods.InterfaceA: void print()>");
    SootMethod mainMethodPrint =
        Scene.v().getMethod("<soot.defaultInterfaceMethods.InterfaceReAbstracting: void print()>");

    Body mainBody = target.retrieveActiveBody();
    SootMethod refMainMethod = resolveMethodRefInBody(mainBody.getUnits(), "void print()");
    SootMethod resolvedMethod =
        VirtualCalls.v()
            .resolveNonSpecial(Scene.v().getRefType(testClass), interfaceAPrint.makeRef(), false);
    SootMethod concreteImpl =
        Scene.v()
            .getFastHierarchy()
            .resolveConcreteDispatch(Scene.v().getSootClass(testClass), interfaceAPrint);
    Set<SootMethod> abstractImpl =
        Scene.v()
            .getFastHierarchy()
            .resolveAbstractDispatch(Scene.v().getSootClass(defaultClass), interfaceAPrint);

    boolean edgeMainMethodToMainPrint = checkInEdges(mainMethodPrint, target);
    boolean edgeMainMethodToInterfaceAPrint = checkInEdges(interfaceAPrint, target);
    final ReachableMethods reachableMethods = Scene.v().getReachableMethods();

    List<SootMethod> targetMethods =
        new ArrayList<SootMethod>() {
          {
            add(mainMethodPrint);
            add(interfaceAPrint);
          }
        };

    for (SootMethod targetMethod : targetMethods) {
      Assert.assertNotNull(targetMethod);
    }
    assertEquals(targetMethods.get(0), refMainMethod);
    assertEquals(targetMethods.get(0).getName(), "print");
    assertTrue(edgeMainMethodToMainPrint);
    assertFalse(edgeMainMethodToInterfaceAPrint);
    assertTrue(reachableMethods.contains(targetMethods.get(0)));
    assertFalse(reachableMethods.contains(targetMethods.get(1)));
    assertEquals(targetMethods.get(0), resolvedMethod);
    assertEquals(targetMethods.get(0), concreteImpl);
    assertNotEquals(targetMethods.get(1), concreteImpl);
    assertEquals(
        Sets.newHashSet(
            Scene.v()
                .getMethod("<soot.defaultInterfaceMethods.InterfaceReAbstracting: void print()>")),
        abstractImpl);
  }

}