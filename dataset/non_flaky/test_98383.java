class DummyClass_98383 {
  @Test
  public void testReminder3WrongOrderEventCreation() {
    final Event reminder2 = new Event();
    reminder2.setTag((Tag.reminder2.toString()));
    reminder2.setTimestamp(Timestamp.from(Instant.now().plus(2, ChronoUnit.DAYS)));
    final Event reminder3 = new Event();
    reminder3.setTag((Tag.reminder3.toString()));
    reminder3.setTimestamp(Timestamp.from(Instant.now().plus(1, ChronoUnit.DAYS)));
    final List<Event> events = new ArrayList<>();
    events.add(reminder2);
    CTPException actualException = null;
    try {
      reminderValidator.validate(events, reminder3, CollectionExerciseState.CREATED);
    } catch (CTPException expectedException) {
      actualException = expectedException;
    }
    assertNotNull(actualException);
    assertEquals(
        "Collection exercise events must be set sequentially", actualException.getMessage());
  }

}