class DummyClass_98428 {
  @Test
  public void testPatchCollectionExerciseExerciseRef() throws Exception {
    CollectionExercise existing = setupCollectionExercise();
    CollectionExerciseDTO collex = new CollectionExerciseDTO();
    SurveyDTO survey = FixtureHelper.loadClassFixtures(SurveyDTO[].class).get(0);
    UUID surveyId = UUID.fromString(survey.getId());
    String exerciseRef = "209966";
    collex.setExerciseRef(exerciseRef);
    collex.setSurveyId(surveyId.toString());
    when(surveyService.findSurvey(surveyId)).thenReturn(survey);
    this.collectionExerciseService.patchCollectionExercise(existing.getId(), collex);

    ArgumentCaptor<CollectionExercise> captor = ArgumentCaptor.forClass(CollectionExercise.class);
    verify(this.collexRepo).saveAndFlush(captor.capture());

    CollectionExercise ce = captor.getValue();
    assertEquals(exerciseRef, ce.getExerciseRef());
    assertNotNull(ce.getUpdated());
  }

}