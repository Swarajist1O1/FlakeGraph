class DummyClass_98401 {
  @Test
  public void testInvalidExerciseEndEventUpdate() {
    final List<Event> events = createMandatoryEvents();

    final Event exerciseEndEvent = new Event();
    exerciseEndEvent.setTag(Tag.exercise_end.toString());
    exerciseEndEvent.setTimestamp(Timestamp.from(Instant.now().plus(1, ChronoUnit.DAYS)));

    CTPException actualException = null;
    try {
      mandatoryValidator.validate(events, exerciseEndEvent, CollectionExerciseState.SCHEDULED);
    } catch (CTPException expectedException) {
      actualException = expectedException;
    }
    assertNotNull(actualException);
    assertEquals(
        "Collection exercise events must be set sequentially", actualException.getMessage());
  }

}