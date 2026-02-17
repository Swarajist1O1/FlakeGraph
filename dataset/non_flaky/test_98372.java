class DummyClass_98372 {
  @Test
  public void testValidExerciseEndEventCreation() throws CTPException {
    List<Event> events = getExistingEvents();
    Instant timestamp = Instant.now().plus(8, ChronoUnit.DAYS);
    Event newExerciseEndEvent = new Event();
    newExerciseEndEvent.setTag((EventService.Tag.exercise_end.toString()));
    newExerciseEndEvent.setTimestamp(Timestamp.from(timestamp));
    validator.validate(
        events, newExerciseEndEvent, CollectionExerciseDTO.CollectionExerciseState.CREATED);
  }

}