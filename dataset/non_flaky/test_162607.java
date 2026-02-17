class DummyClass_162607 {
  @Test
  public void shouldCollectArrayVirtualField() {
    ReferenceCollector collector = new ReferenceCollector(s -> false);
    collector.collectReferencesFromAdvice(
        VirtualFieldTestClasses.UsingArrayAsFieldAdvice.class.getName());
    collector.prune();

    VirtualFieldMappings virtualFieldMappings = collector.getVirtualFieldMappings();
    assertThat(virtualFieldMappings.entrySet())
        .containsExactly(
            entry(
                VirtualFieldTestClasses.Key1.class.getName(),
                Type.getType(Context[].class).getClassName()));
  }

}