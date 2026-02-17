class DummyClass_98399 {
  @Test
  public void testInvalidGoLiveEventUpdate() {
    final List<Event> events = createMandatoryEvents();

    final Event goLiveEvent = new Event();
    goLiveEvent.setTag(Tag.go_live.toString());
    goLiveEvent.setTimestamp(Timestamp.from(Instant.now().plus(8, ChronoUnit.DAYS)));

    CTPException actualException = null;
    try {
      mandatoryValidator.validate(events, goLiveEvent, CollectionExerciseState.SCHEDULED);
    } catch (CTPException expectedException) {
      actualException = expectedException;
    }
    assertNotNull(actualException);
    assertEquals(
        "Collection exercise events must be set sequentially", actualException.getMessage());
  }

}