class DummyClass_98371 {
  @Test
  public void testValidReturnByEventCreation() throws CTPException {
    List<Event> events = getExistingEvents();
    Instant timestamp = Instant.now().plus(6, ChronoUnit.DAYS);
    Event newReturnByEvent = new Event();
    newReturnByEvent.setTag((EventService.Tag.return_by.toString()));
    newReturnByEvent.setTimestamp(Timestamp.from(timestamp));
    validator.validate(
        events, newReturnByEvent, CollectionExerciseDTO.CollectionExerciseState.CREATED);
  }

}