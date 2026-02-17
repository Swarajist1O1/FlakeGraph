class DummyClass_98370 {
  @Test
  public void testValidGoLiveEventCreation() throws CTPException {
    List<Event> events = getExistingEvents();
    Instant timestamp = Instant.now().plus(4, ChronoUnit.DAYS);
    Event newGoLiveEvent = new Event();
    newGoLiveEvent.setTag((EventService.Tag.go_live.toString()));
    newGoLiveEvent.setTimestamp(Timestamp.from(timestamp));
    validator.validate(
        events, newGoLiveEvent, CollectionExerciseDTO.CollectionExerciseState.CREATED);
  }

}