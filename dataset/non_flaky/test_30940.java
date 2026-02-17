class DummyClass_30940 {
  @Test
  public void shouldNotExportDisabledRecordTypes() {
    // given
    config.index.command = false;
    config.index.event = false;
    config.index.rejection = false;
    config.index.deployment = true;

    createAndOpenExporter();

    final RecordType[] recordTypes =
        new RecordType[] {RecordType.COMMAND, RecordType.EVENT, RecordType.COMMAND_REJECTION};

    // when - then
    final Context.RecordFilter filter = testHarness.getContext().getFilter();

    assertThat(Arrays.stream(recordTypes).map(filter::acceptType)).containsOnly(false);
  }

}