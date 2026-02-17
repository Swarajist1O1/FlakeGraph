class DummyClass_156133 {
  @Test
  public void InvokePolymorphic1() {
    final SootMethod testTarget = prepareTarget(
        methodSigFromComponents(TARGET_CLASS, "void invokePolymorphicTarget(java.lang.invoke.MethodHandle)"), TARGET_CLASS);

    // We model invokePolymorphic as invokeVirtual
    final List<InvokeExpr> invokes = invokesFromMethod(testTarget);
    Assert.assertEquals(1, invokes.size());
    final InvokeExpr invokePoly = invokes.get(0);
    Assert.assertTrue(invokePoly instanceof VirtualInvokeExpr);
    final SootMethodRef targetMethodRef = invokePoly.getMethodRef();
    Assert.assertEquals(methodSigFromComponents(METHOD_HANDLE_CLASS, METHOD_HANDLE_INVOKE_SUBSIG),
        targetMethodRef.getSignature());
  }

}