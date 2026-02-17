class DummyClass_98369 {
  @Test
  public void testValidMpsEventCreation() throws CTPException {
    List<Event> events = getExistingEvents();
    Instant timestamp = Instant.now().plus(2, ChronoUnit.DAYS);
    Event newMpsEvent = new Event();
    newMpsEvent.setTag((EventService.Tag.mps.toString()));
    newMpsEvent.setTimestamp(Timestamp.from(timestamp));
    validator.validate(events, newMpsEvent, CollectionExerciseDTO.CollectionExerciseState.CREATED);
  }

}