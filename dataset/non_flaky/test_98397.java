class DummyClass_98397 {
  @Test
  public void testValidExerciseEndEventUpdate() throws CTPException {
    final List<Event> events = createMandatoryEvents();

    final Event exerciseEndEvent = new Event();
    exerciseEndEvent.setTag(Tag.exercise_end.toString());
    exerciseEndEvent.setTimestamp(Timestamp.from(Instant.now().plus(10, ChronoUnit.DAYS)));

    mandatoryValidator.validate(events, exerciseEndEvent, CollectionExerciseState.SCHEDULED);
  }

}