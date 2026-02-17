class DummyClass_156102 {
  @Test
  public void initializedInMethodRef() {
    prepareTarget(methodSigFromComponents(TEST_TARGET_CLASS, "void", "<init>"), TEST_TARGET_CLASS);
    SootClass sootClass = Scene.v().getSootClass("java.util.ArrayDeque");
    assertEquals(SootClass.SIGNATURES, sootClass.resolvingLevel());
  }

}