class DummyClass_98386 {
  @Test
  public void testValidMpsEventCreation() throws CTPException {
    final Event mpsEvent = new Event();
    mpsEvent.setTag((Tag.mps.toString()));
    mpsEvent.setTimestamp(Timestamp.from(Instant.now().plus(2, ChronoUnit.DAYS)));
    final List<Event> events = new ArrayList<>();
    mandatoryValidator.validate(events, mpsEvent, CollectionExerciseState.CREATED);
  }

}