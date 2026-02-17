class DummyClass_98388 {
  @Test
  public void testValidReturnByEventCreation() throws CTPException {
    final Event mpsEvent = new Event();
    mpsEvent.setTag((Tag.mps.toString()));
    mpsEvent.setTimestamp(Timestamp.from(Instant.now().plus(2, ChronoUnit.DAYS)));

    final Event goLiveEvent = new Event();
    goLiveEvent.setTag((Tag.go_live.toString()));
    goLiveEvent.setTimestamp(Timestamp.from(Instant.now().plus(4, ChronoUnit.DAYS)));

    final List<Event> events = Arrays.asList(mpsEvent, goLiveEvent);

    final Event returnByEvent = new Event();
    returnByEvent.setTag((Tag.return_by.toString()));
    returnByEvent.setTimestamp(Timestamp.from(Instant.now().plus(6, ChronoUnit.DAYS)));

    mandatoryValidator.validate(events, returnByEvent, CollectionExerciseState.CREATED);
  }

}