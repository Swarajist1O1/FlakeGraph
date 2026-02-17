class DummyClass_156082 {
  @Test
  public void findsTarget() {
    String methodSignature = methodSigFromComponents(TEST_TARGET_CLASS, "void", "unambiguousMethod", "");
    final SootMethod sootMethod = prepareTarget(methodSignature, TEST_TARGET_CLASS);
    Assert.assertTrue(sootMethod.isConcrete());

    Body body = sootMethod.retrieveActiveBody();
    Assert.assertNotNull(body);
    // validate individual method
    body.validate();

    for (Unit u : body.getUnits()) {
      if (u instanceof AssignStmt) {
        Value right = ((AssignStmt) u).getRightOp();
        if (right instanceof InvokeExpr) {
          SootMethod m = ((InvokeExpr) right).getMethodRef().resolve();
          Assert.assertFalse(m.isPhantom());
          Assert.assertTrue(m.isDeclared());
          if (m.getName().equals("invoke")) {
            Assert.assertTrue(m.isNative());
          }
        }
      }
    }
  }

}