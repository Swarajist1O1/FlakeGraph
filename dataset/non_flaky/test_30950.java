class DummyClass_30950 {
  @Test
  public void shouldFlushOnMemoryLimit() {
    // given
    final var bulkMemoryLimit = 1024;
    final var recordSize = 2;

    configuration.bulk.memoryLimit = bulkMemoryLimit;
    configuration.bulk.size = Integer.MAX_VALUE;
    configuration.bulk.delay = Integer.MAX_VALUE;

    final var variableValue1 = "x".repeat(bulkMemoryLimit / recordSize);
    final var variableValue2 = "y".repeat(bulkMemoryLimit / recordSize);
    final Function<String, String> jsonRecord =
        (String value) -> String.format("{\"value\":\"%s\"}", value);

    final VariableRecordValue recordValue = mock(VariableRecordValue.class);
    when(recordValue.getValue()).thenReturn(variableValue1);

    final Record<VariableRecordValue> recordMock = mock(Record.class);
    when(recordMock.getKey()).thenReturn(1L);
    when(recordMock.getPartitionId()).thenReturn(1);
    when(recordMock.getValueType()).thenReturn(ValueType.VARIABLE);
    when(recordMock.getValue()).thenReturn(recordValue);
    when(recordMock.toJson()).thenReturn(jsonRecord.apply(variableValue1));

    // when
    client.index(recordMock);

    assertThat(client.shouldFlush()).isFalse();

    when(recordMock.getKey()).thenReturn(2L);
    when(recordMock.toJson()).thenReturn(jsonRecord.apply(variableValue2));

    client.index(recordMock);

    // then
    assertThat(client.shouldFlush()).isTrue();
  }

}