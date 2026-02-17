class DummyClass_98433 {
  @Test
  public void testDoNotTransitionToReadyToReviewWhenScheduledWithCIsAndNoSample() throws Exception {
    // Given
    CollectionExercise exercise =
        FixtureHelper.loadClassFixtures(CollectionExercise[].class).get(0);
    exercise.setState(CollectionExerciseDTO.CollectionExerciseState.SCHEDULED);
    given(sampleLinkRepository.findByCollectionExerciseId(exercise.getId()))
        .willReturn(Collections.emptyList());
    String searchStringJson =
        new JSONObject(Collections.singletonMap("COLLECTION_EXERCISE", exercise.getId().toString()))
            .toString();
    given(collectionInstrument.countCollectionInstruments(searchStringJson)).willReturn(1);

    // When
    collectionExerciseService.transitionScheduleCollectionExerciseToReadyToReview(exercise);

    // Then
    exercise.setState(CollectionExerciseDTO.CollectionExerciseState.READY_FOR_REVIEW);
    verify(collexRepo, times(0)).saveAndFlush(exercise);
  }

}