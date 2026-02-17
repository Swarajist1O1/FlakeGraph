class DummyClass_98418 {
  @Test
  public void testNudgeWithSameDateAndTimeEventCreation() {
    final Instant now = Instant.now();
    final Long nudgeTime = now.plus(2, ChronoUnit.DAYS).toEpochMilli();
    final Event goLive = new Event();
    goLive.setTag((EventService.Tag.go_live.toString()));
    goLive.setTimestamp(Timestamp.from(now));

    final Event nudge = new Event();
    nudge.setTag((EventService.Tag.nudge_email_0.toString()));
    nudge.setTimestamp(new Timestamp(nudgeTime));

    final Event returnBy = new Event();
    returnBy.setTag((EventService.Tag.return_by.toString()));
    returnBy.setTimestamp(Timestamp.from(now.plus(4, ChronoUnit.DAYS)));

    final List<Event> events = Arrays.asList(goLive, returnBy, nudge);

    final Event submittedEvent = new Event();
    submittedEvent.setTag((EventService.Tag.nudge_email_1.toString()));
    submittedEvent.setTimestamp(new Timestamp(nudgeTime));

    CTPException actualException = null;
    try {
      nudgeEmailValidator.validate(
          events, submittedEvent, CollectionExerciseDTO.CollectionExerciseState.CREATED);
    } catch (CTPException expectedException) {
      actualException = expectedException;
    }
    assertNotNull(actualException);
    assertEquals(
        "A nudge email has already been scheduled for this date and time. Choose a different date or time.",
        actualException.getMessage());
  }

}