class DummyClass_30945 {
  @Test
  public void shouldNotHandleFlushException() {
    // given
    when(esClient.shouldFlush()).thenReturn(true);
    doThrow(new ElasticsearchExporterException("expected")).when(esClient).flush();

    createAndOpenExporter();

    // when
    assertThatThrownBy(() -> testHarness.export())
        .isInstanceOf(ElasticsearchExporterException.class)
        .withFailMessage("expected");

    // then
    verify(esClient, times(1)).flush();
  }

}