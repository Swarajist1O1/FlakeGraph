class DummyClass_156158 {
  @Test
  public void keepEssentialCast() {
    // create test method and body
    SootClass cl = new SootClass("TestClass", Modifier.PUBLIC);
    SootMethod method = new SootMethod("testMethod", Collections.singletonList(RefType.v("java.lang.Object")),
        ArrayType.v(IntType.v(), 1), Modifier.PUBLIC);
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
    Local d = Jimple.v().newLocal("d", IntType.v());
    locals.add(d);

    // create code
    UnitPatchingChain units = body.getUnits();
    Unit identity0 = Jimple.v().newIdentityStmt(a, Jimple.v().newParameterRef(RefType.v("java.lang.Object"), 0));
    units.add(identity0);
    Unit cast0 = Jimple.v().newAssignStmt(b, Jimple.v().newCastExpr(a, ArrayType.v(IntType.v(), 1)));
    units.add(cast0);
    Unit cast1 = Jimple.v().newAssignStmt(c, Jimple.v().newCastExpr(a, RefType.v("java.lang.Number")));
    units.add(cast1);
    Unit cast2 = Jimple.v().newAssignStmt(d, Jimple.v().newCastExpr(NullConstant.v(), RefType.v("java.lang.Number")));
    units.add(cast2);
    Unit ret = Jimple.v().newReturnStmt(b);
    units.add(ret);

    // execute transform
    DeadAssignmentEliminator.v().internalTransform(body, "testPhase", Collections.emptyMap());

    // check resulting code (cast should be removed)
    Iterator<Unit> it = units.iterator();
    assertEquals(identity0, it.next());
    assertEquals(cast0, it.next());
    assertEquals(cast1, it.next());
    assertEquals(ret, it.next());
    assertEquals(4, units.size());
  }

}