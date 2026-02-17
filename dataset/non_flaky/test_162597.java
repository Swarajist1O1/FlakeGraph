class DummyClass_162597 {
  @Test
  public void shouldCreateReferencesForHelperClasses() {
    ReferenceCollector collector = new ReferenceCollector(s -> false);
    collector.collectReferencesFromAdvice(HelperAdvice.class.getName());
    Map<String, ClassRef> references = collector.getReferences();

    assertThat(references)
        .containsOnlyKeys(
            TestHelperClasses.Helper.class.getName(),
            TestHelperClasses.HelperSuperClass.class.getName(),
            TestHelperClasses.HelperInterface.class.getName());

    ClassRef helperSuperClass = references.get(TestHelperClasses.HelperSuperClass.class.getName());
    assertThat(helperSuperClass.getFlags()).contains(ManifestationFlag.ABSTRACT);
    assertHelperSuperClassMethod(helperSuperClass, true);
    assertMethod(
        helperSuperClass,
        "finalMethod",
        "()Ljava/lang/String;",
        VisibilityFlag.PUBLIC,
        OwnershipFlag.NON_STATIC,
        ManifestationFlag.FINAL);

    ClassRef helperInterface = references.get(TestHelperClasses.HelperInterface.class.getName());
    assertThat(helperInterface.getFlags()).contains(ManifestationFlag.ABSTRACT);
    assertHelperInterfaceMethod(helperInterface, true);

    ClassRef helperClass = references.get(TestHelperClasses.Helper.class.getName());
    assertThat(helperClass.getFlags()).contains(ManifestationFlag.NON_FINAL);
    assertHelperSuperClassMethod(helperClass, false);
    assertHelperInterfaceMethod(helperClass, false);
  }

}