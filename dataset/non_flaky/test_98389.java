class DummyClass_98389 {
  @Test
  public void testInvalidGoLiveEventCreation() {
    final Event mpsEvent = new Event();
    mpsEvent.setTag((Tag.mps.toString()));
    mpsEvent.setTimestamp(Timestamp.from(Instant.now().plus(10, ChronoUnit.DAYS)));

    final Event goLive = new Event();
    goLive.setTag((Tag.go_live.toString()));
    goLive.setTimestamp(Timestamp.from(Instant.now().plus(2, ChronoUnit.DAYS)));

    final List<Event> events = Arrays.asList(mpsEvent);

    CTPException actualException = null;
    try {
      mandatoryValidator.validate(events, goLive, CollectionExerciseState.CREATED);
    } catch (CTPException expectedException) {
      actualException = expectedException;
    }
    assertNotNull(actualException);
    assertEquals(
        "Collection exercise events must be set sequentially", actualException.getMessage());
  }

}