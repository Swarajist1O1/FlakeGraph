class DummyClass_98381 {
  @Test
  public void testReminderBeforeGoliveInvalid() {
    final Event goLive = new Event();
    goLive.setTag((Tag.go_live.toString()));
    goLive.setTimestamp(Timestamp.from(Instant.now().plus(2, ChronoUnit.DAYS)));
    final List<Event> events = Collections.singletonList(goLive);

    final Event reminderEvent = new Event();
    reminderEvent.setTag(Tag.reminder.toString());
    reminderEvent.setTimestamp(Timestamp.from(Instant.now().plus(1, ChronoUnit.DAYS)));

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