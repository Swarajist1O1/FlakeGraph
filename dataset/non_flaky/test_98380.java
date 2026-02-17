class DummyClass_98380 {
  @Test
  public void testReminderAfterExerciseEndInvalid() {
    final Event reminderEvent = new Event();
    reminderEvent.setTag(Tag.reminder3.toString());
    reminderEvent.setTimestamp(Timestamp.from(Instant.now().plus(4, ChronoUnit.DAYS)));

    final Event exerciseEnd = new Event();
    exerciseEnd.setTag((Tag.exercise_end.toString()));
    exerciseEnd.setTimestamp(Timestamp.from(Instant.now().plus(3, ChronoUnit.DAYS)));

    final List<Event> events = Collections.singletonList(exerciseEnd);
    CTPException actualException = null;
    try {
      reminderValidator.validate(events, reminderEvent, CollectionExerciseState.SCHEDULED);
    } catch (CTPException expectedException) {
      actualException = expectedException;
    }
    assertNotNull(actualException);
    assertEquals(
        "Reminder must take place during collection exercise period", actualException.getMessage());
  }

}