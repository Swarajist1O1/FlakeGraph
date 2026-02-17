class DummyClass_156084 {
  @Test
  public void nullAssignment() {
    SootMethod target =
        prepareTarget(
            methodSigFromComponents(TEST_TARGET_CLASS, "void", "nullAssignment"),
            TEST_TARGET_CLASS);

    Body body = target.retrieveActiveBody();

    Optional<Unit> unit =
        body.getUnits().stream()
            .filter(
                u ->
                    u.toString()
                        .equals(
                            "staticinvoke <soot.jimple.PropagateLineNumberTag: soot.jimple.PropagateLineNumberTag$A foo(soot.jimple.PropagateLineNumberTag$A)>(null)"))
            .findFirst();

    assertTrue(unit.isPresent());

    List<ValueBox> useBoxes = unit.get().getUseBoxes();

    assertEquals(2, useBoxes.size());
    ValueBox valueBox = useBoxes.get(0);
    assertTrue(valueBox instanceof ImmediateBox);
    assertEquals(1, valueBox.getTags().size());
    assertTrue(valueBox.getTags().get(0) instanceof LineNumberTag);
    assertEquals(33, valueBox.getJavaSourceStartLineNumber());
  }

}