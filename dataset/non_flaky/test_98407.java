class DummyClass_98407 {
  @Test
  public void testReferenceEndCanBeSetInThePast() throws CTPException {
    final Event refEnd = new Event();
    refEnd.setTag((Tag.ref_period_end.toString()));
    refEnd.setTimestamp(Timestamp.from(Instant.now().minus(1, ChronoUnit.DAYS)));

    final List<Event> events = new ArrayList<>();
    referencePeriodValidator.validate(events, refEnd, CollectionExerciseState.CREATED);
  }

}