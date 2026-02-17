class DummyClass_30938 {
  @Test
  public void shouldNotExportDisabledValueTypes() {
    // given
    config.index.event = true;
    config.index.deployment = false;
    config.index.error = false;
    config.index.incident = false;
    config.index.job = false;
    config.index.jobBatch = false;
    config.index.message = false;
    config.index.messageSubscription = false;
    config.index.variable = false;
    config.index.variableDocument = false;
    config.index.processInstance = false;
    config.index.processInstanceCreation = false;
    config.index.processMessageSubscription = false;

    createAndOpenExporter();

    final ValueType[] valueTypes =
        new ValueType[] {
          ValueType.DEPLOYMENT,
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

    assertThat(Arrays.stream(valueTypes).map(filter::acceptValue)).containsOnly(false);
  }

}