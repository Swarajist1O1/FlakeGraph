class DummyClass_156103 {
  @Test
  public void initializedInConstructor() {
    prepareTarget(methodSigFromComponents(TEST_TARGET_CLASS, "void", "<init>"), TEST_TARGET_CLASS);
    SootClass sootClass = Scene.v().getSootClass("java.util.LinkedList");
    assertEquals(SootClass.SIGNATURES, sootClass.resolvingLevel());
  }

}