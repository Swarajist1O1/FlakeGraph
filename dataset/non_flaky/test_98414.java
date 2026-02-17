class DummyClass_98414 {
  @Test
  public void testCanUpdateNudgeThatHasPastAndCollectionExerciseNotInLockedState()
      throws CTPException {
    final Event nudge = new Event();
    nudge.setTag((EventService.Tag.nudge_email_0.toString()));
    nudge.setTimestamp(Timestamp.from(Instant.now().minus(2, ChronoUnit.DAYS)));

    final Event newNudge = new Event();
    newNudge.setTag((EventService.Tag.nudge_email_0.toString()));
    newNudge.setTimestamp(Timestamp.from(Instant.now().plus(2, ChronoUnit.DAYS)));

    final List<Event> events = Collections.singletonList(nudge);

    nudgeEmailValidator.validate(
        events, newNudge, CollectionExerciseDTO.CollectionExerciseState.SCHEDULED);
  }

}