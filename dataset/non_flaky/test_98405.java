class DummyClass_98405 {
  @Test
  public void canUpdateReferencePeriodWhenCollectionExerciseLive() throws CTPException {
    final Event referencePeriodStart = new Event();
    referencePeriodStart.setTag(Tag.ref_period_start.toString());
    referencePeriodStart.setTimestamp(Timestamp.from(Instant.now()));

    final List<Event> events = new ArrayList<>();

    referencePeriodValidator.validate(events, referencePeriodStart, CollectionExerciseState.LIVE);
  }

}