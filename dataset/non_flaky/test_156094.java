class DummyClass_156094 {
  @Test
  public void nonInner() {
    // statements at the beginning of a for loop should have the line number as for the branching
    // statement and not the last line number after the branch that leads outside the loop
    SootMethod target =
        prepareTarget(
            methodSigFromComponents(TEST_TARGET_CLASS, "void", "method"), TEST_TARGET_CLASS);
    assertEquals(2, Scene.v().getApplicationClasses().size());
    assertFalse(target.getDeclaringClass().hasOuterClass());
    assertFalse(target.getDeclaringClass().isInnerClass());
    InnerClassTag tag = (InnerClassTag) target.getDeclaringClass().getTag(InnerClassTag.NAME);
    // the class has inner classes
    assertNotNull(tag);
  }

}