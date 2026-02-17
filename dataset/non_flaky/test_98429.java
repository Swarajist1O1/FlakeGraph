class DummyClass_98429 {
  @Test
  public void testPatchCollectionExerciseName() throws Exception {
    CollectionExercise existing = setupCollectionExercise();
    CollectionExerciseDTO collex = new CollectionExerciseDTO();
    String name = "Not BRES";
    SurveyDTO survey = FixtureHelper.loadClassFixtures(SurveyDTO[].class).get(0);
    when(surveyService.findSurvey(any())).thenReturn(survey);
    this.collectionExerciseService.patchCollectionExercise(existing.getId(), collex);

    ArgumentCaptor<CollectionExercise> captor = ArgumentCaptor.forClass(CollectionExercise.class);
    verify(this.collexRepo).saveAndFlush(captor.capture());

    CollectionExercise ce = captor.getValue();
    assertNotNull(ce.getUpdated());
  }

}