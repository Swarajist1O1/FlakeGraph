class DummyClass_162605 {
  @Test
  public void shouldCollectMultipleVirtualFieldsForSingleClass() {
    ReferenceCollector collector = new ReferenceCollector(s -> false);
    collector.collectReferencesFromAdvice(
        VirtualFieldTestClasses.TwoVirtualFieldsInTheSameClassAdvice.class.getName());
    collector.prune();

    VirtualFieldMappings virtualFieldMappings = collector.getVirtualFieldMappings();
    assertThat(virtualFieldMappings.entrySet())
        .containsExactlyInAnyOrder(
            entry(VirtualFieldTestClasses.Key1.class.getName(), Context.class.getName()),
            entry(VirtualFieldTestClasses.Key1.class.getName(), State.class.getName()));
  }

}