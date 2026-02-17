class DummyClass_156134 {
  @Test
  public void InvokeCustom1() {
    final SootMethod testTarget
        = prepareTarget(methodSigFromComponents(TARGET_CLASS, "void invokeCustomTarget()"), TARGET_CLASS);

    // We model invokeCustom as invokeDynamic
    final List<InvokeExpr> invokes = invokesFromMethod(testTarget);
    Assert.assertEquals(1, invokes.size());
    final InvokeExpr invokeCustom = invokes.get(0);
    Assert.assertTrue(invokeCustom instanceof DynamicInvokeExpr);
    final SootMethodRef targetMethodRef = invokeCustom.getMethodRef();
    Assert.assertEquals(methodSigFromComponents(SootClass.INVOKEDYNAMIC_DUMMY_CLASS_NAME, SUPPLIER_GET_SUBSIG),
        targetMethodRef.getSignature());
    final String callToLambdaMethaFactory
        = "dynamicinvoke \"get\" <java.util.function.Supplier ()>() <java.lang.invoke.LambdaMetafactory: java.lang.invoke.CallSite metafactory(java.lang.invoke.MethodHandles$Lookup,java.lang.String,java.lang.invoke.MethodType,java.lang.invoke.MethodType,java.lang.invoke.MethodHandle,java.lang.invoke.MethodType)>(methodtype: java.lang.Object __METHODTYPE__(), methodhandle: \"REF_INVOKE_STATIC\" <soot.dexpler.instructions.DexBytecodeTarget: java.lang.String lambda$invokeCustomTarget$0()>, methodtype: java.lang.String __METHODTYPE__())";
    Assert.assertEquals(callToLambdaMethaFactory, invokeCustom.toString());
  }

}