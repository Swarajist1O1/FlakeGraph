class DummyClass_98420 {
  @Test
  public void testCreateCollectionExercise() throws Exception {
    // Given
    CollectionExercise collectionExercise =
        FixtureHelper.loadClassFixtures(CollectionExercise[].class).get(0);
    when(collexRepo.saveAndFlush(any())).thenReturn(collectionExercise);

    SurveyDTO survey = FixtureHelper.loadClassFixtures(SurveyDTO[].class).get(0);
    CollectionExerciseDTO toCreate =
        FixtureHelper.loadClassFixtures(CollectionExerciseDTO[].class).get(0);

    // When
    this.collectionExerciseService.createCollectionExercise(toCreate, survey);

    // Then
    ArgumentCaptor<CollectionExercise> captor = ArgumentCaptor.forClass(CollectionExercise.class);
    verify(this.collexRepo).saveAndFlush(captor.capture());
    CollectionExercise collex = captor.getValue();
    assertEquals(toCreate.getUserDescription(), collex.getUserDescription());
    assertEquals(toCreate.getExerciseRef(), collex.getExerciseRef());
    assertEquals(toCreate.getSurveyId(), collex.getSurveyId().toString());
    assertNotNull(collex.getCreated());
  }

}