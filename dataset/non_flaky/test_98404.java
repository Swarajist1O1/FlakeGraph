class DummyClass_98404 {
  @Test
  public void canUpdateReferencePeriodWhenCollectionExerciseReadyForLive() throws CTPException {
    final Event referencePeriodStart = new Event();
    referencePeriodStart.setTag(Tag.ref_period_end.toString());
    referencePeriodStart.setTimestamp(Timestamp.from(Instant.now()));

    final List<Event> events = new ArrayList<>();

    referencePeriodValidator.validate(
        events, referencePeriodStart, CollectionExerciseState.READY_FOR_LIVE);
  }

}