class DummyClass_162598 {
  @Test
  public void shouldCollectFieldDeclarationReferences() {
    ReferenceCollector collector =
        new ReferenceCollector(s -> s.equals(DeclaredFieldTestClass.Helper.class.getName()));
    collector.collectReferencesFromAdvice(DeclaredFieldTestClass.Advice.class.getName());
    collector.prune();
    Map<String, ClassRef> references = collector.getReferences();

    ClassRef helperClass = references.get(DeclaredFieldTestClass.Helper.class.getName());
    FieldRef superField = findField(helperClass, "superField");
    assertThat(superField).isNotNull();
    assertThat(superField.isDeclared()).isFalse();

    FieldRef field = findField(helperClass, "helperField");
    assertThat(field).isNotNull();
    assertThat(field.isDeclared()).isTrue();

    ClassRef libraryBaseClass =
        references.get(DeclaredFieldTestClass.LibraryBaseClass.class.getName());
    assertThat(libraryBaseClass.getFields()).isEmpty();
  }

}