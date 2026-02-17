class DummyClass_30939 {
  @Test
  public void shouldExportEnabledRecordTypes() {
    // given
    config.index.command = true;
    config.index.event = true;
    config.index.rejection = true;
    config.index.deployment = true;

    createAndOpenExporter();

    final RecordType[] recordTypes =
        new RecordType[] {RecordType.COMMAND, RecordType.EVENT, RecordType.COMMAND_REJECTION};

    // when - then
    final Context.RecordFilter filter = testHarness.getContext().getFilter();

    assertThat(Arrays.stream(recordTypes).map(filter::acceptType)).containsOnly(true);
  }

}