class DummyClass_98412 {
  @Test
  public void testCanUpdateNudgeWhenLive() throws CTPException {
    final Event nudgeEvent = new Event();
    nudgeEvent.setTag(EventService.Tag.nudge_email_0.toString());
    nudgeEvent.setTimestamp(Timestamp.from(Instant.now()));

    final List<Event> events = new ArrayList<>();

    nudgeEmailValidator.validate(
        events, nudgeEvent, CollectionExerciseDTO.CollectionExerciseState.LIVE);
  }

}