class DummyClass_98442 {
  @Test
  public void givenCollectionExcerciseDoesNotExistWhenEventIsCreatedThenExceptionIsThrown() {
    EventDTO eventDto = new EventDTO();
    UUID collexUuid = UUID.randomUUID();
    eventDto.setCollectionExerciseId(collexUuid);

    try {
      eventService.createEvent(eventDto);

      fail("Created event with non-existent collection exercise");
    } catch (CTPException e) {
      // Expected 404
      assertThat(e.getFault(), is(Fault.RESOURCE_NOT_FOUND));
    }
  }

}