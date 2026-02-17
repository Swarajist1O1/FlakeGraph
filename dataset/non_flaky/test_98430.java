class DummyClass_98430 {
  @Test
  public void testPatchCollectionExerciseUserDescription() throws Exception {
    CollectionExercise existing = setupCollectionExercise();
    CollectionExerciseDTO collex = new CollectionExerciseDTO();
    String userDescription = "Really odd description";
    collex.setUserDescription(userDescription);
    SurveyDTO survey = FixtureHelper.loadClassFixtures(SurveyDTO[].class).get(0);
    when(surveyService.findSurvey(any())).thenReturn(survey);
    this.collectionExerciseService.patchCollectionExercise(existing.getId(), collex);

    ArgumentCaptor<CollectionExercise> captor = ArgumentCaptor.forClass(CollectionExercise.class);
    verify(this.collexRepo).saveAndFlush(captor.capture());

    CollectionExercise ce = captor.getValue();
    assertEquals(userDescription, ce.getUserDescription());
    assertNotNull(ce.getUpdated());
  }

}