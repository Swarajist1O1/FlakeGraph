class DummyClass_98443 {
  @Test
  public void givenEventAlreadyExistsWhenEventIsCreatedThenExceptionIsThrown() throws CTPException {
    String tag = Tag.mps.name();
    EventDTO eventDto = new EventDTO();
    CollectionExercise collex = new CollectionExercise();
    UUID collexUuid = UUID.randomUUID();
    eventDto.setCollectionExerciseId(collexUuid);
    eventDto.setTag(tag);
    collex.setId(collexUuid);

    when(collectionExerciseService.findCollectionExercise(collexUuid)).thenReturn(collex);
    when(eventRepository.findOneByCollectionExerciseAndTag(collex, tag)).thenReturn(new Event());

    try {
      eventService.createEvent(eventDto);

      fail("Created event with non-existent collection exercise");
    } catch (CTPException e) {
      // Expected 409
      assertThat(e.getFault(), is(Fault.RESOURCE_VERSION_CONFLICT));
    }
  }

}