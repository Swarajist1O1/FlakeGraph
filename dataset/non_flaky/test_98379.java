class DummyClass_98379 {
  @Test
  public void testValidReminderEventCreation() throws CTPException {
    final Event goLive = new Event();
    goLive.setTag((Tag.go_live.toString()));
    goLive.setTimestamp(Timestamp.from(Instant.now()));

    final Event reminder = new Event();
    reminder.setTag((Tag.reminder.toString()));
    reminder.setTimestamp(Timestamp.from(Instant.now().plus(2, ChronoUnit.DAYS)));

    final Event exerciseEnd = new Event();
    exerciseEnd.setTag((Tag.exercise_end.toString()));
    exerciseEnd.setTimestamp(Timestamp.from(Instant.now().plus(4, ChronoUnit.DAYS)));

    final List<Event> events = Arrays.asList(goLive, exerciseEnd);

    reminderValidator.validate(events, reminder, CollectionExerciseState.CREATED);
  }

}