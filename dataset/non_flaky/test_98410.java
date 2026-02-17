class DummyClass_98410 {
  @Test
  public void returnTrueAndDoNothingIfNotNudge() throws CTPException {
    final Event mpsEvent = new Event();
    mpsEvent.setTag((EventService.Tag.mps.toString()));
    mpsEvent.setTimestamp(Timestamp.from(Instant.now()));
    final List<Event> events = new ArrayList<>();
    nudgeEmailValidator.validate(
        events, mpsEvent, CollectionExerciseDTO.CollectionExerciseState.CREATED);

    verify(eventDateOrderChecker, never()).isEventDatesInOrder(anyList());
  }

}