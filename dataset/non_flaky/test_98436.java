class DummyClass_98436 {
  @Test
  public void testCreateLink() {
    UUID sampleSummaryUuid = UUID.randomUUID(), collexUuid = UUID.randomUUID();

    when(this.sampleLinkRepository.saveAndFlush(any(SampleLink.class))).then(returnsFirstArg());

    SampleLink sampleLink =
        this.collectionExerciseService.createLink(sampleSummaryUuid, collexUuid);

    assertEquals(sampleSummaryUuid, sampleLink.getSampleSummaryId());
    assertEquals(collexUuid, sampleLink.getCollectionExerciseId());

    verify(sampleLinkRepository, times(1)).saveAndFlush(any());
  }

}