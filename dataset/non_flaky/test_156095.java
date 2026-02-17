class DummyClass_156095 {
  @Test
  public void InnerStatic() {
    SootMethod target2 =
        prepareTarget(
            methodSigFromComponents(TEST_TARGET_CLASS + "$Inner", "void", "<init>"),
            TEST_TARGET_CLASS + "$Inner");
    assertEquals(2, Scene.v().getApplicationClasses().size());
    assertTrue(target2.getDeclaringClass().hasOuterClass());
    assertTrue(target2.getDeclaringClass().isInnerClass());
    InnerClassTag tag2 = (InnerClassTag) target2.getDeclaringClass().getTag(InnerClassTag.NAME);
    assertNotNull(tag2);
    assertEquals("soot/asm/ScopeFinderTarget$Inner", tag2.getInnerClass());
    assertEquals("soot/asm/ScopeFinderTarget", tag2.getOuterClass());
    assertTrue(Modifier.isStatic(tag2.getAccessFlags()));
  }

}