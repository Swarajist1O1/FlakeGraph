class DummyClass_98437 {
  @Test
  public void testCreateLinkShouldAttemptToTransitionToReadyToReview() throws CTPException {
    // Given
    UUID sampleSummaryUuid = UUID.randomUUID();
    UUID collexUuid = UUID.randomUUID();
    CollectionExercise collectionExercise = new CollectionExercise();
    collectionExercise.setId(collexUuid);
    collectionExercise.setState(CollectionExerciseDTO.CollectionExerciseState.CREATED);
    given(collexRepo.findOneById(collexUuid)).willReturn(collectionExercise);
    given(collectionInstrument.countCollectionInstruments(any())).willReturn(1);
    SampleSummaryDTO sampleSummary = new SampleSummaryDTO();
    SampleLink sampleLink = new SampleLink();
    sampleLink.setSampleSummaryId(sampleSummaryUuid);
    given(sampleLinkRepository.findByCollectionExerciseId(collexUuid))
        .willReturn(Collections.singletonList(sampleLink));
    sampleSummary.setState(SampleSummaryDTO.SampleState.ACTIVE);
    given(sampleSvcClient.getSampleSummary(sampleSummaryUuid)).willReturn(sampleSummary);

    // When
    this.collectionExerciseService.linkSampleSummaryToCollectionExercise(
        collexUuid, Collections.singletonList(sampleSummaryUuid));

    // Then
    verify(stateManager)
        .transition(
            CollectionExerciseDTO.CollectionExerciseState.CREATED,
            CollectionExerciseDTO.CollectionExerciseEvent.CI_SAMPLE_ADDED);
  }

}