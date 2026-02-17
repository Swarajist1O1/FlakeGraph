class DummyClass_162592 {
  @Test
  public void methodBodyCreatesReferences() {
    ReferenceCollector collector = new ReferenceCollector((String s) -> false);

    collector.collectReferencesFromAdvice(MethodBodyAdvice.class.getName());
    collector.prune();
    Map<String, ClassRef> references = collector.getReferences();

    assertThat(references)
        .containsOnlyKeys(
            MethodBodyAdvice.A.class.getName(),
            MethodBodyAdvice.B.class.getName(),
            MethodBodyAdvice.SomeInterface.class.getName(),
            MethodBodyAdvice.SomeImplementation.class.getName());

    ClassRef refB = references.get(MethodBodyAdvice.B.class.getName());
    ClassRef refA = references.get(MethodBodyAdvice.A.class.getName());

    // interface flags
    assertThat(refB.getFlags()).contains(ManifestationFlag.NON_INTERFACE);
    assertThat(references.get(MethodBodyAdvice.SomeInterface.class.getName()).getFlags())
        .contains(ManifestationFlag.INTERFACE);

    // class access flags
    assertThat(refA.getFlags()).contains(PACKAGE_OR_HIGHER);
    assertThat(refB.getFlags()).contains(PACKAGE_OR_HIGHER);

    // method refs
    assertMethod(
        refB,
        "method",
        "(Ljava/lang/String;)Ljava/lang/String;",
        PROTECTED_OR_HIGHER,
        OwnershipFlag.NON_STATIC);
    assertMethod(
        refB, "methodWithPrimitives", "(Z)V", PROTECTED_OR_HIGHER, OwnershipFlag.NON_STATIC);
    assertMethod(refB, "staticMethod", "()V", PROTECTED_OR_HIGHER, OwnershipFlag.STATIC);
    assertMethod(
        refB,
        "methodWithArrays",
        "([Ljava/lang/String;)[Ljava/lang/Object;",
        PROTECTED_OR_HIGHER,
        OwnershipFlag.NON_STATIC);

    // field refs
    assertThat(refB.getFields()).isEmpty();
    assertThat(refA.getFields()).hasSize(2);
    assertField(refA, "publicB", PACKAGE_OR_HIGHER, OwnershipFlag.NON_STATIC);
    assertField(refA, "staticB", PACKAGE_OR_HIGHER, OwnershipFlag.STATIC);
  }

}