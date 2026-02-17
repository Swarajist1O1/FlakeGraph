class DummyClass_98451 {
  @Test
  public void givenReminderEmailIsDeletedItGetsPropagatedToActionSVC() throws CTPException {

    final CollectionExercise collex = new CollectionExercise();
    collex.setId(COLLEX_UUID);
    collex.setExercisePK(EXERCISE_PK);
    final CollectionExerciseState collectionExerciseState = CollectionExerciseState.SCHEDULED;
    collex.setState(collectionExerciseState);

    when(collectionExerciseService.findCollectionExercise(COLLEX_UUID)).thenReturn(collex);
    final Event existingEvent = new Event();
    existingEvent.setTag(Tag.reminder.toString());
    existingEvent.setId(UUID.randomUUID());
    when(eventRepository.findOneByCollectionExerciseAndTag(collex, Tag.reminder.name()))
        .thenReturn(existingEvent);

    final List<Event> existingEvents = new ArrayList<>();

    eventValidators.add(eventValidator);

    eventService.deleteEvent(COLLEX_UUID, Tag.reminder.name());

    verify(eventRepository, atLeastOnce()).delete(eq(existingEvent));
  }

}