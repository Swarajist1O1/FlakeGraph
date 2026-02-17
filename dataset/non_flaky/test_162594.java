class DummyClass_162594 {
  @Test
  public void ldcCreatesReferences() {
    ReferenceCollector collector = new ReferenceCollector(s -> false);
    collector.collectReferencesFromAdvice(LdcAdvice.class.getName());
    collector.prune();
    Map<String, ClassRef> references = collector.getReferences();

    assertThat(references).containsKey(MethodBodyAdvice.A.class.getName());
  }

}