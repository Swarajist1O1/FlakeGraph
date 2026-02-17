class DummyClass_98456 {
  @Test
  public void testStatusIsSetToScheduledNewEventCreated() {
    final CollectionExercise collex = new CollectionExercise();
    String tag = Tag.mps.name();

    when(collectionExerciseService.findCollectionExercise(COLLEX_UUID)).thenReturn(collex);
    when(eventRepository.save(any(Event.class))).then(returnsFirstArg());

    EventDTO eventDto = new EventDTO();
    eventDto.setCollectionExerciseId(COLLEX_UUID);
    eventDto.setTag(tag);
    eventDto.setTimestamp(new Timestamp(new Date().getTime()));

    try {
      Event event = eventService.createEvent(eventDto);
      assertThat(event.getStatus(), is(EventDTO.Status.SCHEDULED));
    } catch (CTPException e) {
      fail();
    }
  }

}