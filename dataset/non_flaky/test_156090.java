class DummyClass_156090 {
  @Test
  public void iterator() {
    // statements at the beginning of a for loop should have the line number as for the branching
    // statement and not the last line number after the branch that leads outside the loop
    SootMethod target = prepareTarget(methodSigFromComponents(TEST_TARGET_CLASS, "void", "iterator"), TEST_TARGET_CLASS);

    Body body = target.retrieveActiveBody();

    Optional<Unit> unit = body.getUnits().stream()
        .filter(u -> u.toString().contains("<java.util.Iterator: java.lang.Object next()>()")).findFirst();

    Assert.assertTrue(unit.isPresent());

    Assert.assertEquals(31, unit.get().getJavaSourceStartLineNumber());
  }

}