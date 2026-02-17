class DummyClass_156114 {
  @Test
  public void testCachingInvalidation() throws Exception {
    SootMethod m1 = prepareTarget(methodSigFromComponents(TEST_TARGET_CLASS, "void", "m1"), TEST_TARGET_CLASS);
    final SootClass clas = m1.getDeclaringClass();

    // There are only 3 methods in the class originally.
    Assert.assertEquals(Arrays.asList("<init>", "m1", "m2"),
        clas.getMethods().stream().map(SootMethod::getName).sorted().collect(Collectors.toList()));

    // Ensure the previous value of SootMethodRefImpl#resolveCache
    // is not used if the referenced method itself is modified.
    final Body b = m1.retrieveActiveBody();
    final SootMethodRef mRef = getMethodRef(b);
    Assert.assertEquals("m2", mRef.getName());

    // Get the original referenced method appearing in the test source (i.e. "m2")
    final SootMethod origM = mRef.resolve();
    Assert.assertTrue(!origM.isPhantom());
    Assert.assertEquals("m2", origM.getName());

    // Change the name of the method so the method reference no
    // longer refers to that method.
    origM.setName("newMethodName");
    Assert.assertEquals("newMethodName", origM.getName());

    // Changing the method itself does not change the reference
    Assert.assertEquals("m2", mRef.getName());

    // There are still just 3 methods in the class (but "m2" was renamed).
    Assert.assertEquals(Arrays.asList("<init>", "m1", "newMethodName"),
        clas.getMethods().stream().map(SootMethod::getName).sorted().collect(Collectors.toList()));

    // When resolving the reference, the cached value is not used since the
    // original method was renamed. It now gives a different method (that was
    // created automatically since a method with the name "m2" no longer exists).
    final SootMethod newM = mRef.resolve();
    Assert.assertNotSame(origM, newM);
    Assert.assertEquals("m2", newM.getName());

    // There are now 4 methods since resolving "m2" created it again.
    Assert.assertEquals(Arrays.asList("<init>", "m1", "m2", "newMethodName"),
        clas.getMethods().stream().map(SootMethod::getName).sorted().collect(Collectors.toList()));
  }

}