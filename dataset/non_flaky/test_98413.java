class DummyClass_98413 {
  @Test
  public void testCantUpdateNudgeThatHasPastAndCollectionExerciseInLockedState() {
    final Event nudge = new Event();
    nudge.setTag((EventService.Tag.nudge_email_0.toString()));
    nudge.setTimestamp(Timestamp.from(Instant.now().minus(2, ChronoUnit.DAYS)));

    final Event newNudge = new Event();
    newNudge.setTag((EventService.Tag.nudge_email_0.toString()));
    newNudge.setTimestamp(Timestamp.from(Instant.now().plus(2, ChronoUnit.DAYS)));

    final List<Event> events = Collections.singletonList(nudge);
    CTPException actualException = null;
    try {
      nudgeEmailValidator.validate(
          events, newNudge, CollectionExerciseDTO.CollectionExerciseState.LIVE);
    } catch (CTPException expectedException) {
      actualException = expectedException;
    }
    assertNotNull(actualException);
    assertEquals("Nudge email cannot be set in the past", actualException.getMessage());
  }

}