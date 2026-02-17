class DummyClass_156109 {
  @Test
  public void derivedInterfacesTest() {
    String testClassSig = "soot.defaultInterfaceMethods.DerivedInterfaces";
    String defaultInterfaceOneSig = "soot.defaultInterfaceMethods.InterfaceTestOne";
    String defaultInterfaceTwoSig = "soot.defaultInterfaceMethods.InterfaceTestTwo";

    final SootMethod target =
        prepareTarget(
            methodSigFromComponents(testClassSig, voidType, mainClass),
            testClassSig,
            defaultInterfaceOneSig,
            defaultInterfaceTwoSig);

    FastHierarchy fh = Scene.v().getFastHierarchy();
    SootClass testClass = Scene.v().getSootClass(testClassSig);
    SootClass defaultInterfaceOne = Scene.v().getSootClass(defaultInterfaceOneSig);
    SootClass defaultInterfaceTwo = Scene.v().getSootClass(defaultInterfaceTwoSig);

    SootMethod interfaceOnePrint =
        Scene.v().getMethod(methodSigFromComponents(defaultInterfaceOneSig, "void print()"));
    SootMethod interfaceTwoPrint =
        Scene.v().getMethod(methodSigFromComponents(defaultInterfaceTwoSig, "void print()"));

    SootMethod refMainMethod =
        resolveMethodRefInBody(target.retrieveActiveBody().getUnits(), "void print()");

    SootMethod interfaceOneResolvedMethod =
        VirtualCalls.v().resolveNonSpecial(testClass.getType(), interfaceOnePrint.makeRef(), false);
    SootMethod interfaceTwoResolvedMethod =
        VirtualCalls.v().resolveNonSpecial(testClass.getType(), interfaceTwoPrint.makeRef(), false);

    SootMethod concreteImplInterfaceOne = fh.resolveConcreteDispatch(testClass, interfaceOnePrint);
    SootMethod concreteImplInterfaceTwo = fh.resolveConcreteDispatch(testClass, interfaceTwoPrint);

    Set<SootMethod> abstractImplInterfaceOne =
        fh.resolveAbstractDispatch(defaultInterfaceOne, interfaceOnePrint);

    boolean edgeMainToInterfaceTwoPrint = checkInEdges(interfaceTwoPrint, target);
    boolean edgeMainToInterfaceOnePrint = checkInEdges(interfaceOnePrint, target);

    final ReachableMethods reachableMethods = Scene.v().getReachableMethods();

    List<SootMethod> targetMethods =
        new ArrayList<SootMethod>() {
          {
            add(interfaceOnePrint);
            add(interfaceTwoPrint);
          }
        };

    Map<SootMethod, SootMethod> resolvedMethods =
        new HashMap<SootMethod, SootMethod>() {
          {
            put(interfaceTwoPrint, interfaceOneResolvedMethod);
            put(interfaceTwoPrint, interfaceTwoResolvedMethod);
          }
        };

    Map<SootMethod, SootMethod> concreteImplTrue =
        new HashMap<SootMethod, SootMethod>() {
          {
            put(interfaceTwoPrint, concreteImplInterfaceOne);
            put(interfaceTwoPrint, concreteImplInterfaceTwo);
          }
        };

    Map<SootMethod, SootMethod> concreteImplNotTrue =
        new HashMap<SootMethod, SootMethod>() {
          {
            put(interfaceOnePrint, concreteImplInterfaceOne);
            put(interfaceOnePrint, concreteImplInterfaceTwo);
          }
        };

    for (SootMethod targetMethod : targetMethods) {
      Assert.assertNotNull(targetMethod);
    }
    assertEquals(targetMethods.get(1), refMainMethod);
    assertEquals(targetMethods.get(1).getName(), "print");
    assertFalse(edgeMainToInterfaceOnePrint);
    assertTrue(edgeMainToInterfaceTwoPrint);
    assertTrue(reachableMethods.contains(targetMethods.get(1)));
    assertFalse(reachableMethods.contains(targetMethods.get(0)));
    for (Map.Entry<SootMethod, SootMethod> virtualResolvedMethod : resolvedMethods.entrySet()) {
      assertEquals(virtualResolvedMethod.getKey(), virtualResolvedMethod.getValue());
    }
    for (Map.Entry<SootMethod, SootMethod> concreteImpl : concreteImplTrue.entrySet()) {
      assertEquals(concreteImpl.getKey(), concreteImpl.getValue());
    }
    for (Map.Entry<SootMethod, SootMethod> concreteImpl : concreteImplNotTrue.entrySet()) {
      assertNotEquals(concreteImpl.getKey(), concreteImpl.getValue());
    }
    assertEquals(Sets.newHashSet(targetMethods.get(1)), abstractImplInterfaceOne);

    assertEquals(
        Sets.newHashSet(targetMethods.get(1)),
        fh.resolveAbstractDispatch(defaultInterfaceTwo, interfaceTwoPrint));
  }

}