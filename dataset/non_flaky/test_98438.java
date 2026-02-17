class DummyClass_98438 {
  @Test
  public void testFindCollectionExercisesForSurveys() throws Exception {
    final UUID SURVEY_ID_1 = UUID.fromString("31ec898e-f370-429a-bca4-eab1045aff4e");

    List<UUID> surveys = Arrays.asList(SURVEY_ID_1);

    List<CollectionExercise> existing = FixtureHelper.loadClassFixtures(CollectionExercise[].class);

    given(collexRepo.findBySurveyIdInOrderBySurveyId(surveys)).willReturn(existing);

    HashMap<UUID, List<CollectionExercise>> result =
        this.collectionExerciseService.findCollectionExercisesForSurveys(surveys);

    assertEquals(result.get(SURVEY_ID_1).size(), 2);
  }

}