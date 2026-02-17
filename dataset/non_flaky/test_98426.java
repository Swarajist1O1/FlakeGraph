class DummyClass_98426 {
  @Test
  public void testUndeleteCollectionExercise() throws Exception {
    CollectionExercise existing =
        FixtureHelper.loadClassFixtures(CollectionExercise[].class).get(0);
    when(collexRepo.findOneById(existing.getId())).thenReturn(existing);

    this.collectionExerciseService.undeleteCollectionExercise(existing.getId());

    ArgumentCaptor<CollectionExercise> captor = ArgumentCaptor.forClass(CollectionExercise.class);
    verify(this.collexRepo).saveAndFlush(captor.capture());

    assertEquals(false, captor.getValue().getDeleted());
  }

}