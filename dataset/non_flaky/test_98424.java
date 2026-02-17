class DummyClass_98424 {
  @Test
  public void testUpdateCollectionExerciseDoesNotExist() throws Exception {
    CollectionExerciseDTO toUpdate =
        FixtureHelper.loadClassFixtures(CollectionExerciseDTO[].class).get(0);
    UUID updateUuid = UUID.randomUUID();

    try {
      this.collectionExerciseService.updateCollectionExercise(updateUuid, toUpdate);
      fail("Update of non-existent collection exercise succeeded");
    } catch (CTPException e) {
      assertEquals(CTPException.Fault.RESOURCE_NOT_FOUND, e.getFault());
    }
  }

}