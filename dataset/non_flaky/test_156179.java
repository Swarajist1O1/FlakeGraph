class DummyClass_156179 {
  @Test
  public void testSufficientUserVersion() {
    try {
      runSoot("soot.asm.backend.targets.AnnotatedClass", "1.7");
      return;
    } catch (RuntimeException e) {
      fail("Version 1.7 should be sufficient for features of pkg.AnnotatedClass!");
    }
  }

}