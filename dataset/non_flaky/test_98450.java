class DummyClass_98450 {
  @Test
  public void givenEventsForCollectionExerciseValidateWhenEventIsDeletedItIsSaved()
      throws CTPException {

    final CollectionExercise collex = new CollectionExercise();
    collex.setId(COLLEX_UUID);
    collex.setExercisePK(EXERCISE_PK);
    final CollectionExerciseState collectionExerciseState = CollectionExerciseState.SCHEDULED;
    collex.setState(collectionExerciseState);

    when(collectionExerciseService.findCollectionExercise(COLLEX_UUID)).thenReturn(collex);
    final Event existingEvent = new Event();
    existingEvent.setTag(Tag.nudge_email_4.toString());
    existingEvent.setId(UUID.randomUUID());
    when(eventRepository.findOneByCollectionExerciseAndTag(collex, Tag.nudge_email_4.name()))
        .thenReturn(existingEvent);

    final List<Event> existingEvents = new ArrayList<>();

    eventValidators.add(eventValidator);

    eventService.deleteEvent(COLLEX_UUID, Tag.nudge_email_4.name());

    verify(eventRepository, atLeastOnce()).delete(eq(existingEvent));
  }

}