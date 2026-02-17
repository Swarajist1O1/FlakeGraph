class DummyClass_30948 {
  @Test
  public void shouldThrowExceptionIfFailToFlushBulk() {
    // given
    final int bulkSize = 10;

    final Record<VariableRecordValue> recordMock = mock(Record.class);
    when(recordMock.getPartitionId()).thenReturn(1);
    when(recordMock.getValueType()).thenReturn(ValueType.PROCESS_INSTANCE);

    // bulk contains records that fail on flush
    IntStream.range(0, bulkSize)
        .forEach(
            i -> {
              when(recordMock.getKey()).thenReturn(RECORD_KEY + i);
              when(recordMock.toJson()).thenReturn("invalid-json-" + i);
              client.index(recordMock);
            });

    // and one valid record
    when(recordMock.getKey()).thenReturn(RECORD_KEY + bulkSize);
    when(recordMock.toJson()).thenReturn("{}");
    client.index(recordMock);

    // when/then
    assertThatThrownBy(client::flush)
        .isInstanceOf(ElasticsearchExporterException.class)
        .hasMessageContaining(
            "Failed to flush 10 item(s) of bulk request [type: mapper_parsing_exception, reason: failed to parse]");
  }

}