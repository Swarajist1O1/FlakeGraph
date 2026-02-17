class DummyClass_98400 {
  @Test
  public void testInvalidReturnByEventUpdate() {
    final List<Event> events = createMandatoryEvents();

    final Event returnByEvent = new Event();
    returnByEvent.setTag(Tag.return_by.toString());
    returnByEvent.setTimestamp(Timestamp.from(Instant.now().plus(1, ChronoUnit.DAYS)));

    CTPException actualException = null;
    try {
      mandatoryValidator.validate(events, returnByEvent, CollectionExerciseState.SCHEDULED);
    } catch (CTPException expectedException) {
      actualException = expectedException;
    }
    assertNotNull(actualException);
    assertEquals(
        "Collection exercise events must be set sequentially", actualException.getMessage());
  }

}