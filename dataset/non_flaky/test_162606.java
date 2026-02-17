class DummyClass_162606 {
  @ParameterizedTest(name = "{0}")
  public void shouldNotCollectVirtualFieldsForInvalidScenario(
      @SuppressWarnings("unused") String desc, String adviceClassName) {
    ReferenceCollector collector = new ReferenceCollector(s -> false);

    Assertions.assertThatExceptionOfType(MuzzleCompilationException.class)
        .isThrownBy(
            () -> {
              collector.collectReferencesFromAdvice(adviceClassName);
              collector.prune();
            });
  }

}