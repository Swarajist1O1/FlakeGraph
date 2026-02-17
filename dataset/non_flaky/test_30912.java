class DummyClass_30912 {
  @Test
  public void shouldCollectToList() {
    // given
    final RecordingExporter exporter = new RecordingExporter();
    exporter.export(new TestRecord(1));
    exporter.export(new TestRecord(2));
    exporter.export(new TestRecord(3));

    // when
    final List<Record<TestValue>> list =
        records(VALUE_TYPE, TestValue.class).collect(Collectors.toList());

    // then
    assertThat(list).extracting(Record::getPosition).containsExactly(1L, 2L, 3L);
  }

}