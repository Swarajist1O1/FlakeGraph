class DummyClass_156159 {
  @Test
  public void removePrimitiveCast() {
    // create test method and body
    SootClass cl = new SootClass("TestClass", Modifier.PUBLIC);
    SootMethod method = new SootMethod("testMethod", Arrays.asList(IntType.v(), IntType.v()), IntType.v(), Modifier.PUBLIC);
    cl.addMethod(method);
    JimpleBody body = Jimple.v().newBody(method);
    method.setActiveBody(body);

    // create locals
    Chain<Local> locals = body.getLocals();
    Local a = Jimple.v().newLocal("a", IntType.v());
    locals.add(a);
    Local b = Jimple.v().newLocal("b", IntType.v());
    locals.add(b);
    Local c = Jimple.v().newLocal("c", IntType.v());
    locals.add(c);
    Local d = Jimple.v().newLocal("d", DoubleType.v());
    locals.add(d);

    // create code
    UnitPatchingChain units = body.getUnits();
    Unit identity0 = Jimple.v().newIdentityStmt(a, Jimple.v().newParameterRef(IntType.v(), 0));
    units.add(identity0);
    Unit identity1 = Jimple.v().newIdentityStmt(b, Jimple.v().newParameterRef(IntType.v(), 1));
    units.add(identity1);
    Unit addition = Jimple.v().newAssignStmt(c, Jimple.v().newAddExpr(a, b));
    units.add(addition);
    Unit cast = Jimple.v().newAssignStmt(d, Jimple.v().newCastExpr(a, DoubleType.v()));
    units.add(cast);
    Unit ret = Jimple.v().newReturnStmt(c);
    units.add(ret);

    // execute transform
    DeadAssignmentEliminator.v().internalTransform(body, "testPhase", Collections.emptyMap());

    // check resulting code (cast should be removed)
    Iterator<Unit> it = units.iterator();
    assertEquals(identity0, it.next());
    assertEquals(identity1, it.next());
    assertEquals(addition, it.next());
    assertEquals(ret, it.next());
    assertEquals(4, units.size());
  }

}