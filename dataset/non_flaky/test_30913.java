class DummyClass_30913 {
  @Test
  public void shouldReadConfiguration() {
    // when
    final SampleConfiguration actual =
        sutConfigurationFactory.create(
            null,
            "config-test",
            "TestConfigurationFactoryTestSample.yaml",
            SampleConfiguration.class);

    // then
    assertThat(actual.getSetting()).isEqualTo("test");
    assertThat(actual.getTimeout()).isEqualTo(Duration.ofSeconds(3));
    assertThat(actual.getSize()).isEqualTo(DataSize.ofMegabytes(2));
    assertThat(actual.getArgs()).containsOnly(entry("foo", "bar"));
  }

}