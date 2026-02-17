class DummyClass_98422 {
  @Test
  public void testUpdateCollectionExerciseInvalidSurvey() throws Exception {
    CollectionExerciseDTO toUpdate =
        FixtureHelper.loadClassFixtures(CollectionExerciseDTO[].class).get(0);
    CollectionExercise existing =
        FixtureHelper.loadClassFixtures(CollectionExercise[].class).get(0);
    existing.setSurveyId(UUID.randomUUID());
    when(collexRepo.findOneById(existing.getId())).thenReturn(existing);

    try {
      this.collectionExerciseService.updateCollectionExercise(existing.getId(), toUpdate);
      fail("Update collection exercise with null survey succeeded");
    } catch (CTPException e) {
      assertEquals(CTPException.Fault.BAD_REQUEST, e.getFault());
    }
  }

}