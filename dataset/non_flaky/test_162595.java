class DummyClass_162595 {
  @Test
  public void instanceofCreatesReferences() {
    ReferenceCollector collector = new ReferenceCollector(s -> false);
    collector.collectReferencesFromAdvice(TestClasses.InstanceofAdvice.class.getName());
    collector.prune();
    Map<String, ClassRef> references = collector.getReferences();

    assertThat(references).containsKey(MethodBodyAdvice.A.class.getName());
  }

}