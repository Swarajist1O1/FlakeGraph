class DummyClass_98403 {
  @Test
  public void returnTrueAndDoNothingIfNotReferencePeriodEvent() throws CTPException {
    final Event mpsEvent = new Event();
    mpsEvent.setTag((Tag.mps.toString()));
    mpsEvent.setTimestamp(Timestamp.from(Instant.now()));
    final List<Event> events = new ArrayList<>();
    referencePeriodValidator.validate(events, mpsEvent, CollectionExerciseState.CREATED);
  }

}