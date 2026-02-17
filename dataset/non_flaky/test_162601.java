class DummyClass_162601 {
  @Test
  public void shouldCorrectlyFindExternalInstrumentationClasses() {
    ReferenceCollector collector =
        new ReferenceCollector(s -> s.startsWith("external.instrumentation"));
    collector.collectReferencesFromAdvice(
        TestClasses.ExternalInstrumentationAdvice.class.getName());
    collector.prune();

    Map<String, ClassRef> references = collector.getReferences();
    assertThat(references.get("external.NotInstrumentation")).isNotNull();

    List<String> helperClasses = collector.getSortedHelperClasses();
    assertThat(helperClasses).containsExactly(ExternalHelper.class.getName());
  }

}