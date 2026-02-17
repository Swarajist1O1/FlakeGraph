class DummyClass_30934 {
  @Test
  public void shouldExportEvenIfElasticNotInitiallyReachable() {
    // given
    elastic.withPort(SocketUtil.getNextAddress().getPort());
    configuration = getDefaultConfiguration();
    configuration.index.prefix = "zeebe";
    esClient = createElasticsearchClient(configuration);

    // when
    exporterBrokerRule.configure("es", ElasticsearchExporter.class, configuration);
    exporterBrokerRule.start();
    exporterBrokerRule.publishMessage("message", "123");
    elastic.start();

    // then
    RecordingExporter.messageRecords()
        .withCorrelationKey("123")
        .withName("message")
        .forEach(r -> TestUtil.waitUntil(() -> wasExported(r)));
    assertIndexSettings();
  }

}