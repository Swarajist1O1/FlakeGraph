class DummyClass_98385 {
  @Test
  public void returnTrueAndDoNothingIfNotReminder() throws CTPException {
    final Event mpsEvent = new Event();
    mpsEvent.setTag((Tag.reminder.toString()));
    mpsEvent.setTimestamp(Timestamp.from(Instant.now()));
    final List<Event> events = new ArrayList<>();
    mandatoryValidator.validate(events, mpsEvent, CollectionExerciseState.CREATED);

    verify(eventDateOrderChecker, never()).isEventDatesInOrder(anyList());
  }

}