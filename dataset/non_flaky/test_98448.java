class DummyClass_98448 {
  @Test
  public void givenEventDoesNotExistWhenEventIsDeletedThenExceptionIsThrown() {
    final UUID collexUuid = UUID.randomUUID();

    final CollectionExercise collex = new CollectionExercise();
    collex.setId(collexUuid);

    when(collectionExerciseService.findCollectionExercise(collexUuid)).thenReturn(collex);
    when(eventRepository.findOneByCollectionExerciseAndTag(collex, Tag.mps.name()))
        .thenReturn(null);

    try {
      eventService.deleteEvent(collexUuid, Tag.mps.name());

      Assert.fail("Deleted non-existent event");
    } catch (final CTPException e) {
      assertThat(e.getFault(), is(Fault.RESOURCE_NOT_FOUND));
    }
  }

}