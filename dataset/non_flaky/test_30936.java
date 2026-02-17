class DummyClass_30936 {
  @Test
  public void shouldCreateIndexTemplates() {
    // given
    config.index.prefix = "foo-bar";
    config.index.createTemplate = true;
    config.index.deployment = true;
    config.index.process = true;
    config.index.error = true;
    config.index.incident = true;
    config.index.job = true;
    config.index.jobBatch = true;
    config.index.message = true;
    config.index.messageSubscription = true;
    config.index.variable = true;
    config.index.variableDocument = true;
    config.index.processInstance = true;
    config.index.processInstanceCreation = true;
    config.index.processMessageSubscription = true;

    // when
    createAndOpenExporter();
    testHarness.export();

    // then
    verify(esClient).putComponentTemplate("foo-bar", "foo-bar", ZEEBE_RECORD_TEMPLATE_JSON);

    verify(esClient).putIndexTemplate(ValueType.DEPLOYMENT);
    verify(esClient).putIndexTemplate(ValueType.PROCESS);
    verify(esClient).putIndexTemplate(ValueType.ERROR);
    verify(esClient).putIndexTemplate(ValueType.INCIDENT);
    verify(esClient).putIndexTemplate(ValueType.JOB);
    verify(esClient).putIndexTemplate(ValueType.JOB_BATCH);
    verify(esClient).putIndexTemplate(ValueType.MESSAGE);
    verify(esClient).putIndexTemplate(ValueType.MESSAGE_SUBSCRIPTION);
    verify(esClient).putIndexTemplate(ValueType.VARIABLE);
    verify(esClient).putIndexTemplate(ValueType.VARIABLE_DOCUMENT);
    verify(esClient).putIndexTemplate(ValueType.PROCESS_INSTANCE);
    verify(esClient).putIndexTemplate(ValueType.PROCESS_INSTANCE_CREATION);
    verify(esClient).putIndexTemplate(ValueType.PROCESS_MESSAGE_SUBSCRIPTION);
  }

}