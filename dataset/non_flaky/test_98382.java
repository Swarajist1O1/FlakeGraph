class DummyClass_98382 {
  @Test
  public void testReminder2WrongOrderEventCreation() {
    final Event reminder = new Event();
    reminder.setTag((Tag.reminder.toString()));
    reminder.setTimestamp(Timestamp.from(Instant.now().plus(2, ChronoUnit.DAYS)));
    final Event reminder2 = new Event();
    reminder2.setTag((Tag.reminder2.toString()));
    reminder2.setTimestamp(Timestamp.from(Instant.now().plus(1, ChronoUnit.DAYS)));
    final List<Event> events = new ArrayList<>();
    events.add(reminder);
    CTPException actualException = null;
    try {
      reminderValidator.validate(events, reminder2, CollectionExerciseState.CREATED);
    } catch (CTPException expectedException) {
      actualException = expectedException;
    }
    assertNotNull(actualException);
    assertEquals(
        "Collection exercise events must be set sequentially", actualException.getMessage());
  }

}