class DummyClass_156112 {
  @Test
  public void superClassPreferenceOverDefaultMethodTest() {
    String testClass = "soot.defaultInterfaceMethods.SuperClassPreferenceOverDefaultMethod";
    String defaultInterfaceOne = "soot.defaultInterfaceMethods.InterfaceOne";
    String defaultInterfaceTwo = "soot.defaultInterfaceMethods.InterfaceTwo";
    String defaultSuperClass = "soot.defaultInterfaceMethods.SuperClass";

    final SootMethod target =
        prepareTarget(
            methodSigFromComponents(testClass, voidType, mainClass),
            testClass,
            defaultInterfaceOne,
            defaultInterfaceTwo,
            defaultSuperClass);

    SootMethod interfaceOnePrint =
        Scene.v().getMethod("<soot.defaultInterfaceMethods.InterfaceOne: void print()>");
    SootMethod interfaceTwoPrint =
        Scene.v().getMethod("<soot.defaultInterfaceMethods.InterfaceTwo: void print()>");
    SootMethod superClassPrint =
        Scene.v().getMethod("<soot.defaultInterfaceMethods.SuperClass: void print()>");

    Body mainBody = target.retrieveActiveBody();
    SootMethod refMainMethod = resolveMethodRefInBody(mainBody.getUnits(), "void print()");

    SootMethod resolvedInterfaceOneDefaultMethod =
        VirtualCalls.v()
            .resolveNonSpecial(Scene.v().getRefType(testClass), interfaceOnePrint.makeRef(), false);
    SootMethod resolvedInterfaceTwoDefaultMethod =
        VirtualCalls.v()
            .resolveNonSpecial(Scene.v().getRefType(testClass), interfaceTwoPrint.makeRef(), false);

    SootMethod concreteImplInterfaceOne =
        Scene.v()
            .getFastHierarchy()
            .resolveConcreteDispatch(Scene.v().getSootClass(testClass), interfaceOnePrint);
    SootMethod concreteImplInterfaceTwo =
        Scene.v()
            .getFastHierarchy()
            .resolveConcreteDispatch(Scene.v().getSootClass(testClass), interfaceTwoPrint);

    Set<SootMethod> abstractImplInterfaceOne =
        Scene.v()
            .getFastHierarchy()
            .resolveAbstractDispatch(
                Scene.v().getSootClass(defaultInterfaceOne), interfaceOnePrint);
    Set<SootMethod> abstractImplInterfaceTwo =
        Scene.v()
            .getFastHierarchy()
            .resolveAbstractDispatch(
                Scene.v().getSootClass(defaultInterfaceTwo), interfaceTwoPrint);

    boolean edgeMainToInterfaceOnePrint = checkInEdges(interfaceOnePrint, target);
    boolean edgeMainToInterfaceTwoPrint = checkInEdges(interfaceTwoPrint, target);
    boolean edgeMainToSuperClassPrint = checkInEdges(superClassPrint, target);

    final ReachableMethods reachableMethods = Scene.v().getReachableMethods();

    List<SootMethod> targetMethods =
        new ArrayList<SootMethod>() {
          {
            add(superClassPrint);
            add(interfaceOnePrint);
            add(interfaceTwoPrint);
          }
        };

    ArrayList<Boolean> edgeNotPresent =
        new ArrayList<Boolean>() {
          {
            add(edgeMainToInterfaceOnePrint);
            add(edgeMainToInterfaceTwoPrint);
          }
        };

    Map<SootMethod, SootMethod> resolvedMethods =
        new HashMap<SootMethod, SootMethod>() {
          {
            put(superClassPrint, resolvedInterfaceOneDefaultMethod);
            put(superClassPrint, resolvedInterfaceTwoDefaultMethod);
          }
        };

    Map<SootMethod, SootMethod> concreteImplTrue =
        new HashMap<SootMethod, SootMethod>() {
          {
            put(superClassPrint, concreteImplInterfaceOne);
            put(superClassPrint, concreteImplInterfaceTwo);
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
      assertNotNull(targetMethod);
    }
    assertEquals(targetMethods.get(0), refMainMethod);
    assertEquals(targetMethods.get(0).getName(), "print");
    assertTrue(edgeMainToSuperClassPrint);
    for (boolean notPresent : edgeNotPresent) {
      assertFalse(notPresent);
    }
    assertTrue(reachableMethods.contains(targetMethods.get(0)));
    assertFalse(reachableMethods.contains(targetMethods.get(1)));
    assertFalse(reachableMethods.contains(targetMethods.get(2)));
    for (Map.Entry<SootMethod, SootMethod> virtualResolvedMethod : resolvedMethods.entrySet()) {
      assertEquals(virtualResolvedMethod.getKey(), virtualResolvedMethod.getValue());
    }
    for (Map.Entry<SootMethod, SootMethod> concreteImpl : concreteImplTrue.entrySet()) {
      assertEquals(concreteImpl.getKey(), concreteImpl.getValue());
    }
    for (Map.Entry<SootMethod, SootMethod> concreteImpl : concreteImplNotTrue.entrySet()) {
      assertNotEquals(concreteImpl.getKey(), concreteImpl.getValue());
    }
    assertTrue(
        abstractImplInterfaceOne.contains(
            Scene.v().getMethod("<soot.defaultInterfaceMethods.SuperClass: void print()>")));
    assertTrue(
        abstractImplInterfaceTwo.contains(
            Scene.v().getMethod("<soot.defaultInterfaceMethods.SuperClass: void print()>")));
  }

}