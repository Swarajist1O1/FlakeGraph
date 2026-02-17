class DummyClass_156085 {
  @Test
  public void transitiveNullAssignment() {
    SootMethod target =
        prepareTarget(
            methodSigFromComponents(TEST_TARGET_CLASS, "void", "transitiveNullAssignment"),
            TEST_TARGET_CLASS);

    Body body = target.retrieveActiveBody();

    // first call to foo
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
    assertEquals(39, valueBox.getJavaSourceStartLineNumber());

    // second call to foo
    unit =
        body.getUnits().stream()
            .filter(
                u ->
                    u.toString()
                        .equals(
                            "staticinvoke <soot.jimple.PropagateLineNumberTag: soot.jimple.PropagateLineNumberTag$A foo(soot.jimple.PropagateLineNumberTag$A)>(null)"))
            .skip(1)
            .findFirst();

    assertTrue(unit.isPresent());
    useBoxes = unit.get().getUseBoxes();
    assertEquals(2, useBoxes.size());
    valueBox = useBoxes.get(0);
    assertTrue(valueBox instanceof ImmediateBox);
    assertEquals(1, valueBox.getTags().size());
    assertTrue(valueBox.getTags().get(0) instanceof LineNumberTag);
    assertEquals(39, valueBox.getJavaSourceStartLineNumber());
  }

}