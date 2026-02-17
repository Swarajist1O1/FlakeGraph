class DummyClass_98378 {
  @Test
  public void testCanUpdateReminderThatHasPastAndCollectionExerciseNotInLockedState()
      throws CTPException {
    final Event reminder = new Event();
    reminder.setTag((Tag.reminder.toString()));
    reminder.setTimestamp(Timestamp.from(Instant.now().minus(2, ChronoUnit.DAYS)));

    final Event newReminder = new Event();
    newReminder.setTag((Tag.reminder.toString()));
    newReminder.setTimestamp(Timestamp.from(Instant.now().plus(2, ChronoUnit.DAYS)));

    final List<Event> events = Collections.singletonList(reminder);

    reminderValidator.validate(events, newReminder, CollectionExerciseState.SCHEDULED);
  }

}