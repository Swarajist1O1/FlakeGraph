class DummyClass_30946 {
  @Test
  public void shouldFailOnWrongPrefix() {
    // given
    config.index.prefix = "prefix_withunderscore";

    createExporterAndTestHarness();

    // then
    assertThatThrownBy(() -> testHarness.configure("els", config))
        .isInstanceOf(ExporterException.class)
        .withFailMessage(
            "Elasticsearch prefix must not contain underscore. Current value: "
                + config.index.prefix);
  }

}