class DummyClass_98431 {
  @Test
  public void testPatchCollectionExerciseNonUnique() throws Exception {
    CollectionExerciseDTO toUpdate =
        FixtureHelper.loadClassFixtures(CollectionExerciseDTO[].class).get(0);
    CollectionExercise existing =
        FixtureHelper.loadClassFixtures(CollectionExercise[].class).get(0);
    existing.setSurveyId(UUID.randomUUID());
    // Set up the mock to return the one we are attempting to update
    when(collexRepo.findOneById(existing.getId())).thenReturn(existing);

    UUID uuid = UUID.fromString("0f66744b-bfdb-458a-b495-1eb605462003");
    CollectionExercise otherExisting = new CollectionExercise();
    otherExisting.setId(uuid);
    // Set up the mock to return a different one with the same exercise ref and survey id
    when(collexRepo.findByExerciseRefAndSurveyId(
            toUpdate.getExerciseRef(), UUID.fromString(toUpdate.getSurveyId())))
        .thenReturn(Collections.singletonList(otherExisting));

    try {
      this.collectionExerciseService.patchCollectionExercise(existing.getId(), toUpdate);

      fail("Update to collection exercise breaking uniqueness constraint succeeded");
    } catch (CTPException e) {
      assertEquals(CTPException.Fault.RESOURCE_VERSION_CONFLICT, e.getFault());
    }
  }

}