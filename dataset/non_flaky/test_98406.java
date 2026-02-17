class DummyClass_98406 {
  @Test
  public void testReferenceStartCanBeSetInThePast() throws CTPException {
    final Event refStart = new Event();
    refStart.setTag((Tag.ref_period_start.toString()));
    refStart.setTimestamp(Timestamp.from(Instant.now().minus(1, ChronoUnit.DAYS)));

    final List<Event> events = new ArrayList<>();
    referencePeriodValidator.validate(events, refStart, CollectionExerciseState.CREATED);
  }

}