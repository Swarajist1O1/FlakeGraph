class DummyClass_98447 {
  @Test
  public void givenEventDoesNotExistWhenEventIsUpdatedThenExceptionIsThrown() {
    final UUID collexUuid = UUID.randomUUID();

    final CollectionExercise collex = new CollectionExercise();
    collex.setId(collexUuid);

    when(collectionExerciseService.findCollectionExercise(collexUuid)).thenReturn(collex);
    when(eventRepository.findOneByCollectionExerciseAndTag(collex, Tag.mps.name()))
        .thenReturn(null);

    try {
      eventService.updateEvent(collexUuid, Tag.mps.name(), new Date());

      Assert.fail("Updated non-existent event");
    } catch (final CTPException e) {
      assertThat(e.getFault(), is(Fault.RESOURCE_NOT_FOUND));
    }
  }

}