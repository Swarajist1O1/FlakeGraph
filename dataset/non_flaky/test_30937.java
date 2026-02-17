class DummyClass_30937 {
  @Test
  public void shouldExportEnabledValueTypes() {
    // given
    config.index.event = true;
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

    createAndOpenExporter();

    final ValueType[] valueTypes =
        new ValueType[] {
          ValueType.DEPLOYMENT,
          ValueType.PROCESS,
          ValueType.ERROR,
          ValueType.INCIDENT,
          ValueType.JOB,
          ValueType.JOB_BATCH,
          ValueType.MESSAGE,
          ValueType.MESSAGE_SUBSCRIPTION,
          ValueType.VARIABLE,
          ValueType.VARIABLE_DOCUMENT,
          ValueType.PROCESS_INSTANCE,
          ValueType.PROCESS_INSTANCE_CREATION,
          ValueType.PROCESS_MESSAGE_SUBSCRIPTION
        };

    // when - then
    final Context.RecordFilter filter = testHarness.getContext().getFilter();

    assertThat(Arrays.stream(valueTypes).map(filter::acceptValue)).containsOnly(true);
  }

}