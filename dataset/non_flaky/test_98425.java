class DummyClass_98425 {
  @Test
  public void testDeleteCollectionExercise() throws Exception {
    CollectionExercise existing =
        FixtureHelper.loadClassFixtures(CollectionExercise[].class).get(0);
    when(collexRepo.findOneById(existing.getId())).thenReturn(existing);

    this.collectionExerciseService.deleteCollectionExercise(existing.getId());

    ArgumentCaptor<CollectionExercise> captor = ArgumentCaptor.forClass(CollectionExercise.class);
    verify(this.collexRepo).saveAndFlush(captor.capture());

    assertEquals(true, captor.getValue().getDeleted());
  }

}