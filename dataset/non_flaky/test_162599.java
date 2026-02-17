class DummyClass_162599 {
  @Test
  public void shouldFindAllHelperClasses() {
    ReferenceCollector collector = new ReferenceCollector(s -> false);
    collector.collectReferencesFromAdvice(HelperAdvice.class.getName());
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