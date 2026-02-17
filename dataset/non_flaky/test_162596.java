class DummyClass_162596 {
  @Test
  public void invokedynamicCreatesReferences() {
    ReferenceCollector collector = new ReferenceCollector(s -> false);
    collector.collectReferencesFromAdvice(TestClasses.InvokeDynamicAdvice.class.getName());
    collector.prune();
    Map<String, ClassRef> references = collector.getReferences();

    assertThat(references).containsKey("muzzle.TestClasses$MethodBodyAdvice$SomeImplementation");
    assertMethod(
        references.get("muzzle.TestClasses$MethodBodyAdvice$SomeImplementation"),
        "someMethod",
        "()V",
        PROTECTED_OR_HIGHER,
        OwnershipFlag.NON_STATIC);
    assertThat(references).containsKey("muzzle.TestClasses$MethodBodyAdvice$B");
    assertMethod(
        references.get("muzzle.TestClasses$MethodBodyAdvice$B"),
        "staticMethod",
        "()V",
        PROTECTED_OR_HIGHER,
        OwnershipFlag.STATIC);
    assertThat(references).containsKey("muzzle.TestClasses$MethodBodyAdvice$A");
    assertMethod(
        references.get("muzzle.TestClasses$MethodBodyAdvice$A"),
        "<init>",
        "()V",
        PROTECTED_OR_HIGHER,
        OwnershipFlag.NON_STATIC);
  }

}