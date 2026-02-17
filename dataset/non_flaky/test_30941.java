class DummyClass_30941 {
  @Test
  public void shouldUpdateLastPositionOnFlush() {
    // given
    when(esClient.shouldFlush()).thenReturn(true);

    // when
    createAndOpenExporter();
    final Record record =
        testHarness.export(
            r ->
                r.getMetadata()
                    .setValueType(ValueType.PROCESS_INSTANCE)
                    .setRecordType(RecordType.EVENT));

    // then
    assertThat(testHarness.getController().getPosition()).isEqualTo(record.getPosition());
  }

}