class DummyClass_98449 {
  @Test
  public void givenEventsForCollectionExerciseValidateWhenEventIsUpdatedItIsSaved()
      throws CTPException {

    final CollectionExercise collex = new CollectionExercise();
    collex.setId(COLLEX_UUID);
    collex.setExercisePK(EXERCISE_PK);
    final CollectionExerciseState collectionExerciseState = CollectionExerciseState.SCHEDULED;
    collex.setState(collectionExerciseState);

    when(collectionExerciseService.findCollectionExercise(COLLEX_UUID)).thenReturn(collex);
    final Event existingEvent = new Event();
    when(eventRepository.findOneByCollectionExerciseAndTag(collex, Tag.mps.name()))
        .thenReturn(existingEvent);

    final List<Event> existingEvents = new ArrayList<>();

    when(eventRepository.findByCollectionExercise(collex)).thenReturn(existingEvents);
    eventValidators.add(eventValidator);

    eventService.updateEvent(COLLEX_UUID, Tag.mps.name(), new Date());

    verify(eventRepository, atLeastOnce()).save(eq(existingEvent));
  }

}