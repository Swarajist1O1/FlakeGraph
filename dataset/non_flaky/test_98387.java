class DummyClass_98387 {
  @Test
  public void testValidGoLiveEventCreation() throws CTPException {
    final Event mpsEvent = new Event();
    mpsEvent.setTag((Tag.mps.toString()));
    mpsEvent.setTimestamp(Timestamp.from(Instant.now().plus(2, ChronoUnit.DAYS)));

    final Event goLiveEvent = new Event();
    goLiveEvent.setTag((Tag.go_live.toString()));
    goLiveEvent.setTimestamp(Timestamp.from(Instant.now().plus(4, ChronoUnit.DAYS)));

    final List<Event> events = Arrays.asList(mpsEvent);

    mandatoryValidator.validate(events, goLiveEvent, CollectionExerciseState.CREATED);
  }

}