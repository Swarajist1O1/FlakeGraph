class DummyClass_30949 {
  @Test
  public void shouldIgnoreRecordIfDuplicateOfLast() {
    // given
    final Record<VariableRecordValue> recordMock = mock(Record.class);
    when(recordMock.getPartitionId()).thenReturn(1);
    when(recordMock.getValueType()).thenReturn(ValueType.PROCESS_INSTANCE);
    when(recordMock.getKey()).thenReturn(RECORD_KEY + 1);
    when(recordMock.toJson()).thenReturn("{}");

    client.index(recordMock);
    assertThat(bulkRequest).hasSize(1);

    // when
    client.index(recordMock);

    // then
    assertThat(bulkRequest).hasSize(1);
  }

}