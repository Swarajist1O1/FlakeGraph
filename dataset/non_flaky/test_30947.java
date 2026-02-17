class DummyClass_30947 {
  @Test
  public void shouldExportRecords() {
    // given
    elasticConfigurator.accept(elastic);
    elastic.start();

    // given
    configuration = getDefaultConfiguration();
    exporterConfigurator.accept(configuration);

    // when
    exporterBrokerRule.configure("es", ElasticsearchExporter.class, configuration);
    exporterBrokerRule.start();
    exporterBrokerRule.performSampleWorkload();

    // then

    // assert index settings for all created indices
    esClient = createElasticsearchClient(configuration);
    assertIndexSettings();

    // assert all records which where recorded during the tests where exported
    exporterBrokerRule.visitExportedRecords(
        r -> {
          if (configuration.shouldIndexRecord(r)) {
            assertRecordExported(r);
          }
        });
  }

}