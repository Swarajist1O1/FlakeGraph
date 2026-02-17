class DummyClass_98427 {
  @Test
  public void testPatchCollectionExerciseNotExists() throws Exception {
    CollectionExerciseDTO toUpdate =
        FixtureHelper.loadClassFixtures(CollectionExerciseDTO[].class).get(0);
    UUID updateUuid = UUID.randomUUID();

    try {
      this.collectionExerciseService.patchCollectionExercise(updateUuid, toUpdate);

      fail("Attempt to patch non-existent collection exercise succeeded");
    } catch (CTPException e) {
      assertEquals(CTPException.Fault.RESOURCE_NOT_FOUND, e.getFault());
    }
  }

}