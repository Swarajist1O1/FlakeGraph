class DummyClass_162600 {
  @Test
  public void shouldCorrectlyFindHelperClassesFromMultipleAdviceClasses() {
    ReferenceCollector collector = new ReferenceCollector(s -> false);
    collector.collectReferencesFromAdvice(HelperAdvice.class.getName());
    collector.collectReferencesFromAdvice(TestClasses.HelperOtherAdvice.class.getName());
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
    assertThat(helperClasses)
        .containsSubsequence(
            Arrays.asList(
                OtherTestHelperClasses.TestEnum.class.getName(),
                OtherTestHelperClasses.TestEnum.class.getName() + "$1"));

    assertThat(helperClasses)
        .containsExactlyInAnyOrder(
            TestHelperClasses.HelperSuperClass.class.getName(),
            TestHelperClasses.HelperInterface.class.getName(),
            TestHelperClasses.Helper.class.getName(),
            OtherTestHelperClasses.Bar.class.getName(),
            OtherTestHelperClasses.Foo.class.getName(),
            OtherTestHelperClasses.TestEnum.class.getName(),
            OtherTestHelperClasses.TestEnum.class.getName() + "$1",
            OtherTestHelperClasses.class.getName() + "$1");
  }

}