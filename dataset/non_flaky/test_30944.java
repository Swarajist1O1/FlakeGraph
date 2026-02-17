class DummyClass_30944 {
  @Test
  public void shouldUpdatePositionAfterDelay() {
    // given
    config.index.event = true;
    createAndOpenExporter();

    // when
    final List<Record> exported =
        testHarness.stream(
                r ->
                    r.getMetadata()
                        .setValueType(ValueType.PROCESS_INSTANCE)
                        .setRecordType(RecordType.EVENT))
            .export(4);
    testHarness.getController().runScheduledTasks(Duration.ofSeconds(config.bulk.delay));

    // then record was indexed and the exporter record position was updated
    verify(esClient, times(4)).index(any());
    assertThat(testHarness.getController().getPosition()).isEqualTo(exported.get(3).getPosition());
  }

}