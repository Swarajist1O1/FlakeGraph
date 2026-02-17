class DummyClass_30943 {
  @Test
  public void shouldFlushAfterDelay() {
    // given
    config.index.event = true;
    config.index.processInstance = true;
    config.bulk.delay = 10;

    // scenario: bulk size is not reached still we want to flush
    config.bulk.size = Integer.MAX_VALUE;
    when(esClient.shouldFlush()).thenReturn(false);
    createAndOpenExporter();

    // when
    testHarness.export(
        r ->
            r.getMetadata()
                .setValueType(ValueType.PROCESS_INSTANCE)
                .setRecordType(RecordType.EVENT));

    // then
    assertThat(testHarness.getController().getScheduledTasks()).hasSize(1);
    assertThat(testHarness.getController().getScheduledTasks().get(0).getDelay())
        .isEqualTo(Duration.ofSeconds(config.bulk.delay));

    // and
    testHarness.getController().runScheduledTasks(Duration.ofSeconds(config.bulk.delay));
    verify(esClient).flush();
  }

}