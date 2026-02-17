class DummyClass_98415 {
  @Test
  public void testValidNudgeEventCreation() throws CTPException {
    final Event goLive = new Event();
    goLive.setTag((EventService.Tag.go_live.toString()));
    goLive.setTimestamp(Timestamp.from(Instant.now()));

    final Event nudge = new Event();
    nudge.setTag((EventService.Tag.nudge_email_0.toString()));
    nudge.setTimestamp(Timestamp.from(Instant.now().plus(2, ChronoUnit.DAYS)));

    final Event returnBy = new Event();
    returnBy.setTag((EventService.Tag.return_by.toString()));
    returnBy.setTimestamp(Timestamp.from(Instant.now().plus(4, ChronoUnit.DAYS)));

    final List<Event> events = Arrays.asList(goLive, returnBy);

    nudgeEmailValidator.validate(
        events, nudge, CollectionExerciseDTO.CollectionExerciseState.CREATED);
  }

}