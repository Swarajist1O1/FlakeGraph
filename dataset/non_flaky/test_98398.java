class DummyClass_98398 {
  @Test
  public void testInvalidMpsEventUpdate() {
    final List<Event> events = createMandatoryEvents();

    final Event mpsEvent = new Event();
    mpsEvent.setTag(Tag.mps.toString());
    mpsEvent.setTimestamp(Timestamp.from(Instant.now().plus(6, ChronoUnit.DAYS)));

    CTPException actualException = null;
    try {
      mandatoryValidator.validate(events, mpsEvent, CollectionExerciseState.SCHEDULED);
    } catch (CTPException expectedException) {
      actualException = expectedException;
    }
    assertNotNull(actualException);
    assertEquals(
        "Collection exercise events must be set sequentially", actualException.getMessage());
  }

}