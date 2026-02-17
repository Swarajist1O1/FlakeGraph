class DummyClass_162593 {
  @Test
  public void protectedRefTest() {
    ReferenceCollector collector = new ReferenceCollector(s -> false);
    collector.collectReferencesFromAdvice(MethodBodyAdvice.B2.class.getName());
    collector.prune();
    Map<String, ClassRef> references = collector.getReferences();

    assertMethod(
        references.get(MethodBodyAdvice.B.class.getName()),
        "protectedMethod",
        "()V",
        PROTECTED_OR_HIGHER,
        OwnershipFlag.NON_STATIC);
  }

}