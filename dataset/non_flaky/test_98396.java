class DummyClass_98396 {
  @Test
  public void testValidReturnByEventUpdate() throws CTPException {
    final List<Event> events = createMandatoryEvents();

    final Event returnByEvent = new Event();
    returnByEvent.setTag(Tag.return_by.toString());
    returnByEvent.setTimestamp(Timestamp.from(Instant.now().plus(5, ChronoUnit.DAYS)));

    mandatoryValidator.validate(events, returnByEvent, CollectionExerciseState.SCHEDULED);
  }

}