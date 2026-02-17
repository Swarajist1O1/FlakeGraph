class DummyClass_156091 {
  @Test
  public void localNaming() {
    // This test ensures that local names are preserved in the Jimple code.
    final String className = "soot.asm.LocalNaming";
    final String[] params = { "java.lang.String", "java.lang.Integer", "byte[]", "java.lang.StringBuilder" };
    SootMethod target = prepareTarget(methodSigFromComponents(className, "void", "localNaming", params), className);
    Body body = target.retrieveActiveBody();
    Set<String> localNames = body.getLocals().stream().map(Local::getName).collect(Collectors.toSet());

    // All expected Local names are present
    Assert.assertTrue(localNames.contains("alpha"));
    Assert.assertTrue(localNames.contains("beta"));
    Assert.assertTrue(localNames.contains("gamma"));
    Assert.assertTrue(localNames.contains("delta"));
    Assert.assertTrue(localNames.contains("epsilon"));
    Assert.assertTrue(localNames.contains("zeta"));
    Assert.assertTrue(localNames.contains("eta"));
    Assert.assertTrue(localNames.contains("theta"));
    Assert.assertTrue(localNames.contains("iota"));
    Assert.assertTrue(localNames.contains("omega"));

    // No Local name contains "$stack"
    Assert.assertTrue(localNames.stream().allMatch(n -> !n.contains("$stack")));
  }

}