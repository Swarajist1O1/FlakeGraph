class DummyClass_156178 {
  @Test
  public void testMinimalVersionAnnotation() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Enforced Java version 1.3 too low to support required features (1.5 required)");
    runSoot("soot.asm.backend.targets.AnnotatedClass", "1.3");

  }

}