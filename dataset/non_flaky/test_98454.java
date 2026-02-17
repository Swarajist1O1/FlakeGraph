class DummyClass_98454 {
  @Test
  public void givenCollectionExerciseDoesNotExistWhenCreatingAnExceptionThrowError() {
    final String tag = Tag.mps.name();
    final EventDTO eventDto = new EventDTO();
    final UUID collexUuid = UUID.randomUUID();
    eventDto.setCollectionExerciseId(collexUuid);
    eventDto.setTag(tag);
    when(collectionExerciseService.findCollectionExercise(collexUuid)).thenReturn(null);
    try {
      eventService.createEvent(eventDto);
      fail("Created event with non-existent collection exercise");
    } catch (final CTPException e) {
      assertThat(e.getFault(), is(Fault.RESOURCE_NOT_FOUND));
    }
  }

}