class DummyClass_156132 {
  @Test
  public void nullAssignment() {
    SootMethod target =
        prepareTarget(methodSigFromComponents(TEST_TARGET_CLASS, "void", "prefixVariableNames"), TEST_TARGET_CLASS);

    Body body = target.retrieveActiveBody();
    Assert.assertTrue(body instanceof JimpleBody);

    // Assert all local names are distinct
    Assert.assertTrue(body.getLocals().stream().map(Local::getName).distinct().count() == body.getLocalCount());

    LocalPacker.v().transform(body);

    // Assert all local names are distinct
    Assert.assertTrue(body.getLocals().stream().map(Local::getName).distinct().count() == body.getLocalCount());
  }

}