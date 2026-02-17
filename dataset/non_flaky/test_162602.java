class DummyClass_162602 {
  @ParameterizedTest
  public void shouldCollectHelperClassesFromResourceFile(
      @SuppressWarnings("unused") String desc, String resource) {
    ReferenceCollector collector = new ReferenceCollector(s -> false);
    collector.collectReferencesFromResource(HelperResource.create(resource, resource));
    collector.prune();

    List<String> helperClasses = collector.getSortedHelperClasses();
    assertThat(helperClasses)
        .containsSubsequence(
            Arrays.asList(
                TestHelperClasses.HelperInterface.class.getName(),
                TestHelperClasses.Helper.class.getName()));
    assertThat(helperClasses)
        .containsSubsequence(
            Arrays.asList(
                TestHelperClasses.HelperSuperClass.class.getName(),
                TestHelperClasses.Helper.class.getName()));
  }

}