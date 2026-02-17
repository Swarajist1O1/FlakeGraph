class DummyClass_98455 {
  @Test
  public void givenCollectionExerciseEventsAreInInvalidStateThrowException() {
    final String tag = Tag.mps.name();
    final EventDTO eventDto = new EventDTO();
    final CollectionExercise collex = new CollectionExercise();
    final UUID collexUuid = UUID.randomUUID();
    eventDto.setCollectionExerciseId(collexUuid);
    eventDto.setTag(tag);
    eventDto.setTimestamp(new Timestamp(Instant.now().toEpochMilli()));
    collex.setId(collexUuid);
    when(collectionExerciseService.findCollectionExercise(collexUuid)).thenReturn(collex);
    when(eventRepository.findOneByCollectionExerciseAndTag(collex, Tag.mps.name()))
        .thenReturn(null);
    final List<Event> existingEvents = new ArrayList<>();
    final Event event = new Event();
    existingEvents.add(event);
    when(eventRepository.findByCollectionExercise(collex)).thenReturn(existingEvents);
    eventValidators.add(eventValidator);
    try {
      eventService.createEvent(eventDto);
    } catch (final CTPException e) {
      assertThat(e.getFault(), is(Fault.BAD_REQUEST));
    }
  }

}