class DummyClass_156106 {
  @Test
  public void interfaceSameSignatureTest() {
    String testClassSig = "soot.defaultInterfaceMethods.InterfaceSameSignature";
    String interfaceReadSig = "soot.defaultInterfaceMethods.Read";
    String interfaceWriteSig = "soot.defaultInterfaceMethods.Write";    

    final SootMethod target =
        prepareTarget(
            methodSigFromComponents(testClassSig, voidType, mainClass),
            testClassSig,
            interfaceReadSig,
            interfaceWriteSig);

    SootClass testClass = Scene.v().getSootClass(testClassSig);
    SootClass interfaceRead = Scene.v().getSootClass(interfaceReadSig);
    SootClass interfaceWrite = Scene.v().getSootClass(interfaceWriteSig);

    SootMethod mainPrintMethod =
        Scene.v().getMethod("<soot.defaultInterfaceMethods.InterfaceSameSignature: void print()>");
    SootMethod readInterfacePrint =
        Scene.v().getMethod("<soot.defaultInterfaceMethods.Read: void print()>");
    SootMethod writeInterfacePrint =
        Scene.v().getMethod("<soot.defaultInterfaceMethods.Write: void print()>");
    SootMethod readInterfaceRead =
        Scene.v().getMethod("<soot.defaultInterfaceMethods.Read: void read()>");
    SootMethod writeInterfaceWrite =
        Scene.v().getMethod("<soot.defaultInterfaceMethods.Write: void write()>");

    Body mainBody = target.retrieveActiveBody();
    Body mainPrintBody = mainPrintMethod.retrieveActiveBody();

    SootMethod refMainMethod = resolveMethodRefInBody(mainBody.getUnits(), "void print()");
    SootMethod refWritePrintMethod =
        resolveMethodRefInBody(
            mainPrintBody.getUnits(), "soot.defaultInterfaceMethods.Write: void print()");
    SootMethod refReadPrintMethod =
        resolveMethodRefInBody(
            mainPrintBody.getUnits(), "soot.defaultInterfaceMethods.Read: void print()");
    SootMethod refDefaultRead = resolveMethodRefInBody(mainBody.getUnits(), "void read()");
    SootMethod refDefaultWrite = resolveMethodRefInBody(mainBody.getUnits(), "void write()");

    SootMethod resolvedMainMethod =
        VirtualCalls.v()
            .resolveNonSpecial(
                Scene.v().getRefType(testClassSig), mainPrintMethod.makeRef(), false);
    SootMethod resolvedWritePrintMethod =
        VirtualCalls.v()
            .resolveNonSpecial(
                Scene.v().getRefType(testClassSig), writeInterfacePrint.makeRef(), false);
    SootMethod resolvedReadPrintMethod =
        VirtualCalls.v()
            .resolveNonSpecial(
                Scene.v().getRefType(testClassSig), readInterfacePrint.makeRef(), false);
    SootMethod resolvedDefaultReadMethod =
        VirtualCalls.v()
            .resolveNonSpecial(
                Scene.v().getRefType(testClassSig), readInterfaceRead.makeRef(), false);
    SootMethod resolvedDefaultWriteMethod =
        VirtualCalls.v()
            .resolveNonSpecial(
                Scene.v().getRefType(testClassSig), writeInterfaceWrite.makeRef(), false);

    FastHierarchy fh = Scene.v().getFastHierarchy();
    SootMethod concreteImplMainPrint = fh.resolveConcreteDispatch(testClass, mainPrintMethod);
    SootMethod concreteImplWritePrint = fh.resolveConcreteDispatch(testClass, refWritePrintMethod);
    SootMethod concreteImplReadPrint = fh.resolveConcreteDispatch(testClass, refReadPrintMethod);
    SootMethod concreteImplDefaultRead = fh.resolveConcreteDispatch(testClass, refDefaultRead);
    SootMethod concreteImplDefaultWrite = fh.resolveConcreteDispatch(testClass, refDefaultWrite);

    assertEquals(
        Sets.newHashSet(readInterfaceRead),
        fh.resolveAbstractDispatch(interfaceRead, refDefaultRead));
    assertEquals(
        Sets.newHashSet(writeInterfaceWrite),
        fh.resolveAbstractDispatch(interfaceWrite, refDefaultWrite));
    assertEquals(
        Sets.newHashSet(mainPrintMethod),
        fh.resolveAbstractDispatch(interfaceRead, refReadPrintMethod));
    assertEquals(
        Sets.newHashSet(mainPrintMethod),
        fh.resolveAbstractDispatch(interfaceWrite, refWritePrintMethod));

    /* Edges should be present */
    boolean edgeMainPrintToReadPrint = checkInEdges(readInterfacePrint, mainPrintMethod);
    boolean edgeMainPrintToWritePrint = checkInEdges(writeInterfacePrint, mainPrintMethod);
    boolean edgeMainMethodToPrint = checkInEdges(mainPrintMethod, target);
    boolean edgeMainMethodToReadMethod = checkInEdges(readInterfaceRead, target);
    boolean edgeMainMethodToWriteMethod = checkInEdges(writeInterfaceWrite, target);

    /* Edges should not be present */
    boolean edgeMainMethodToReadPrint = checkInEdges(readInterfacePrint, target);
    boolean edgeMainMethodToWritePrint = checkInEdges(writeInterfacePrint, target);

    final ReachableMethods reachableMethods = Scene.v().getReachableMethods();

    /* Arguments for assert function */
    Map<SootMethod, String> targetMethods =
        new HashMap<SootMethod, String>() {
          {
            put(mainPrintMethod, "print");
            put(readInterfacePrint, "print");
            put(writeInterfacePrint, "print");
            put(readInterfaceRead, "read");
            put(writeInterfaceWrite, "write");
          }
        };

    Map<SootMethod, SootMethod> resolvedMethods =
        new HashMap<SootMethod, SootMethod>() {
          {
            put(mainPrintMethod, resolvedMainMethod);
            put(mainPrintMethod, resolvedWritePrintMethod);
            put(mainPrintMethod, resolvedReadPrintMethod);
            put(readInterfaceRead, resolvedDefaultReadMethod);
            put(writeInterfaceWrite, resolvedDefaultWriteMethod);
          }
        };

    Map<SootMethod, SootMethod> methodRef =
        new HashMap<SootMethod, SootMethod>() {
          {
            put(mainPrintMethod, refMainMethod);
            put(writeInterfacePrint, refWritePrintMethod);
            put(readInterfacePrint, refReadPrintMethod);
            put(readInterfaceRead, refDefaultRead);
            put(writeInterfaceWrite, refDefaultWrite);
          }
        };

    Map<SootMethod, SootMethod> concreteImpl =
        new HashMap<SootMethod, SootMethod>() {
          {
            put(mainPrintMethod, concreteImplMainPrint);
            put(mainPrintMethod, concreteImplWritePrint);
            put(mainPrintMethod, concreteImplReadPrint);
            put(readInterfaceRead, concreteImplDefaultRead);
            put(writeInterfaceWrite, concreteImplDefaultWrite);
          }
        };

    ArrayList<Boolean> edgePresent =
        new ArrayList<Boolean>() {
          {
            add(edgeMainPrintToReadPrint);
            add(edgeMainPrintToWritePrint);
            add(edgeMainMethodToPrint);
            add(edgeMainMethodToReadMethod);
            add(edgeMainMethodToWriteMethod);
          }
        };

    ArrayList<Boolean> edgeNotPresent =
        new ArrayList<Boolean>() {
          {
            add(edgeMainMethodToReadPrint);
            add(edgeMainMethodToWritePrint);
          }
        };

    for (Map.Entry<SootMethod, String> targetMethod : targetMethods.entrySet()) {
      assertNotNull(targetMethod.getKey());
    }
    for (Map.Entry<SootMethod, SootMethod> virtualResolvedMethod : resolvedMethods.entrySet()) {
      assertEquals(virtualResolvedMethod.getKey(), virtualResolvedMethod.getValue());
    }
    for (Map.Entry<SootMethod, SootMethod> methodRef1 : methodRef.entrySet()) {
      assertEquals(methodRef1.getKey(), methodRef1.getValue());
    }
    for (Map.Entry<SootMethod, String> targetMethod : targetMethods.entrySet()) {
      assertEquals(targetMethod.getKey().getName(), targetMethod.getValue());
    }
    for (Map.Entry<SootMethod, String> targetMethod : targetMethods.entrySet()) {
      assertTrue(reachableMethods.contains(targetMethod.getKey()));
    }
    for (boolean isPresent : edgePresent) {
      assertTrue(isPresent);
    }
    for (boolean notPresent : edgeNotPresent) {
      assertFalse(notPresent);
    }
    for (Map.Entry<SootMethod, SootMethod> concreteImpl1 : concreteImpl.entrySet()) {
      assertEquals(concreteImpl1.getKey(), concreteImpl1.getValue());
    }
  }

}