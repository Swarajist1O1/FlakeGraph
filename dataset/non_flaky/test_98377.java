class DummyClass_98377 {
  @Test
  public void testCantUpdateReminderThatHasPastAndCollectionExerciseInLockedState() {
    final Event reminder = new Event();
    reminder.setTag((Tag.reminder.toString()));
    reminder.setTimestamp(Timestamp.from(Instant.now().minus(2, ChronoUnit.DAYS)));

    final Event newReminder = new Event();
    newReminder.setTag((Tag.reminder.toString()));
    newReminder.setTimestamp(Timestamp.from(Instant.now().plus(2, ChronoUnit.DAYS)));

    final List<Event> events = Collections.singletonList(reminder);
    CTPException actualException = null;
    try {
      reminderValidator.validate(events, newReminder, CollectionExerciseState.LIVE);
    } catch (CTPException expectedException) {
      actualException = expectedException;
    }
    assertNotNull(actualException);
    assertEquals("Reminder cannot be set in the past", actualException.getMessage());
  }

}