class DummyClass_98432 {
  @Test
  public void testTransitionToReadyToReviewWhenScheduledWithCIsAndSample() throws Exception {
    // Given
    CollectionExercise exercise =
        FixtureHelper.loadClassFixtures(CollectionExercise[].class).get(0);
    exercise.setState(CollectionExerciseDTO.CollectionExerciseState.SCHEDULED);
    SampleLink testSampleLink = new SampleLink();
    testSampleLink.setSampleSummaryId(UUID.randomUUID());
    given(sampleLinkRepository.findByCollectionExerciseId(exercise.getId()))
        .willReturn(Collections.singletonList(testSampleLink));

    SampleSummaryDTO sampleSummary = new SampleSummaryDTO();
    sampleSummary.setState(SampleSummaryDTO.SampleState.ACTIVE);
    given(sampleSvcClient.getSampleSummary(testSampleLink.getSampleSummaryId()))
        .willReturn(sampleSummary);

    String searchStringJson =
        new JSONObject(Collections.singletonMap("COLLECTION_EXERCISE", exercise.getId().toString()))
            .toString();
    given(collectionInstrument.countCollectionInstruments(searchStringJson)).willReturn(1);

    // When
    collectionExerciseService.transitionScheduleCollectionExerciseToReadyToReview(exercise);

    // Then
    exercise.setState(CollectionExerciseDTO.CollectionExerciseState.READY_FOR_REVIEW);
    verify(collexRepo).saveAndFlush(exercise);
  }

}