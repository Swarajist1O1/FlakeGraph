class DummyClass_98374 {
  @Test
  public void returnTrueAndDoNothingIfNotReminder() throws CTPException {
    final Event mpsEvent = new Event();
    mpsEvent.setTag((Tag.mps.toString()));
    mpsEvent.setTimestamp(Timestamp.from(Instant.now()));
    final List<Event> events = new ArrayList<>();
    reminderValidator.validate(events, mpsEvent, CollectionExerciseState.CREATED);

    verify(eventDateOrderChecker, never()).isEventDatesInOrder(anyList());
  }

}