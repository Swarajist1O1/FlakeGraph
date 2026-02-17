class DummyClass_30935 {
  @Test
  public void shouldNotFailOnOpenIfElasticIsUnreachable() {
    // given
    final ElasticsearchClient client =
        Mockito.spy(new ElasticsearchClient(config, LoggerFactory.getLogger("test")));
    final ElasticsearchExporter exporter = createExporter(client);
    config.index.createTemplate = true;

    // when - then : only fails when trying to export, not before
    openExporter(exporter);
    assertThatThrownBy(testHarness::export).isInstanceOf(ElasticsearchExporterException.class);
  }

}