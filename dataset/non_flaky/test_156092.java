class DummyClass_156092 {
  @Test
  public void testSilsDisabled() {
    final String className = "soot.asm.LocalNaming";
    final String[] params = {};
    SootMethod target = prepareTarget(methodSigFromComponents(className, "void", "test", params), className);
    Body body = target.retrieveActiveBody();
    Set<String> localNames = body.getLocals().stream().map(Local::getName).collect(Collectors.toSet());
    // test if all expected Local names are present
    Assert.assertTrue(localNames.contains("d"));
    Assert.assertTrue(localNames.contains("f"));
    Assert.assertTrue(localNames.contains("arr"));
  }

}