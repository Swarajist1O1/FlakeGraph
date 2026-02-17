class DummyClass_30942 {
  @Test
  public void shouldFlushOnClose() {
    // given
    createAndOpenExporter();

    // when
    testHarness.close();

    // then
    verify(esClient).flush();
  }

}