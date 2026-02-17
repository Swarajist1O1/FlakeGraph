class DummyClass_98411 {
  @Test
  public void testCanUpdateNudgeWhenReadyForLive() throws CTPException {
    final Event nudgeEvent = new Event();
    nudgeEvent.setTag(EventService.Tag.nudge_email_0.toString());
    nudgeEvent.setTimestamp(Timestamp.from(Instant.now()));

    final List<Event> events = new ArrayList<>();
    nudgeEmailValidator.validate(
        events, nudgeEvent, CollectionExerciseDTO.CollectionExerciseState.READY_FOR_LIVE);
  }

}