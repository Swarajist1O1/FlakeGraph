class DummyClass_98376 {
  @Test
  public void testCanUpdateReminderWhenLive() throws CTPException {
    final Event reminderEvent = new Event();
    reminderEvent.setTag(Tag.reminder.toString());
    reminderEvent.setTimestamp(Timestamp.from(Instant.now()));

    final List<Event> events = new ArrayList<>();

    reminderValidator.validate(events, reminderEvent, CollectionExerciseState.LIVE);
  }

}