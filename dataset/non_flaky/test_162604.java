class DummyClass_162604 {
  @Test
  public void shouldCollectVirtualFields() {
    ReferenceCollector collector = new ReferenceCollector(s -> false);
    collector.collectReferencesFromAdvice(VirtualFieldTestClasses.ValidAdvice.class.getName());
    collector.prune();

    VirtualFieldMappings virtualFieldMappings = collector.getVirtualFieldMappings();
    assertThat(virtualFieldMappings.entrySet())
        .containsExactlyInAnyOrder(
            entry(VirtualFieldTestClasses.Key1.class.getName(), Context.class.getName()),
            entry(VirtualFieldTestClasses.Key2.class.getName(), Context.class.getName()));
  }

}