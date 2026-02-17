class DummyClass_98375 {
  @Test
  public void testCanUpdateReminderWhenReadyForLive() throws CTPException {
    final Event reminderEvent = new Event();
    reminderEvent.setTag(Tag.reminder.toString());
    reminderEvent.setTimestamp(Timestamp.from(Instant.now()));

    final List<Event> events = new ArrayList<>();
    reminderValidator.validate(events, reminderEvent, CollectionExerciseState.READY_FOR_LIVE);
  }

}