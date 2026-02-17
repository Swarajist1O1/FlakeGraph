class DummyClass_162603 {
  @Test
  public void shouldIgnoreArbitraryResourceFile() {
    ReferenceCollector collector = new ReferenceCollector(s -> false);
    collector.collectReferencesFromResource(
        HelperResource.create("application.properties", "application.properties"));
    collector.prune();

    assertThat(collector.getReferences()).isEmpty();
    assertThat(collector.getSortedHelperClasses()).isEmpty();
  }

}