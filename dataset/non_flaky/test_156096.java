class DummyClass_156096 {
  @Test
  public void InnerStaticInner() {
    SootMethod target3 =
        prepareTarget(
            methodSigFromComponents(TEST_TARGET_CLASS + "$Inner$InnerInner", "void", "method"),
            TEST_TARGET_CLASS + "$Inner$InnerInner");
    // one dummy
    assertEquals(2, Scene.v().getApplicationClasses().size());
    assertTrue(target3.getDeclaringClass().hasOuterClass());
    assertTrue(target3.getDeclaringClass().isInnerClass());
    InnerClassTag innerClassTag = null;
    for (Tag tag : target3.getDeclaringClass().getTags()) {
      // FIXME: we have multiple innerclasstags? for a parent it makes sense but for a child class?
      if (tag instanceof InnerClassTag) {
        boolean inner =
            ((InnerClassTag) tag)
                .getInnerClass()
                .equals("soot/asm/ScopeFinderTarget$Inner$InnerInner");
        if (inner) {
          innerClassTag = (InnerClassTag) tag;
          break;
        }
      }
    }
    assertNotNull(innerClassTag);
    assertEquals("soot/asm/ScopeFinderTarget$Inner$InnerInner", innerClassTag.getInnerClass());
    assertEquals("soot/asm/ScopeFinderTarget$Inner", innerClassTag.getOuterClass());
    assertFalse(Modifier.isStatic(innerClassTag.getAccessFlags()));
  }

}