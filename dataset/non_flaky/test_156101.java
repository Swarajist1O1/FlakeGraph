class DummyClass_156101 {
  @Test
  public void testSilsEnabled() {
    final String className = "soot.asm.LocalNaming";
    final String[] params = {};
    SootMethod target = prepareTarget(methodSigFromComponents(className, "void", "test", params), className);
    Body body = target.retrieveActiveBody();
    Set<String> localNames = body.getLocals().stream().map(Local::getName).collect(Collectors.toSet());
    // test if all expected Local names are present
    // currently d, f are not preserved.
    Assert.assertTrue(localNames.contains("d"));
    Assert.assertTrue(localNames.contains("f"));
    Assert.assertTrue(localNames.contains("arr"));
  }

}