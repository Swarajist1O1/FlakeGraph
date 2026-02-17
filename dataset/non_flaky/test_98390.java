class DummyClass_98390 {
  @Test
  public void testInvalidReturnByEventCreation() {
    final Event mpsEvent = new Event();
    mpsEvent.setTag((Tag.mps.toString()));
    mpsEvent.setTimestamp(Timestamp.from(Instant.now().plus(2, ChronoUnit.DAYS)));

    final Event goLiveEvent = new Event();
    goLiveEvent.setTag((Tag.go_live.toString()));
    goLiveEvent.setTimestamp(Timestamp.from(Instant.now().plus(4, ChronoUnit.DAYS)));

    final List<Event> events = Arrays.asList(mpsEvent, goLiveEvent);

    final Event returnByEvent = new Event();
    returnByEvent.setTag((Tag.return_by.toString()));
    returnByEvent.setTimestamp(Timestamp.from(Instant.now().plus(2, ChronoUnit.DAYS)));

    CTPException actualException = null;
    try {
      mandatoryValidator.validate(events, returnByEvent, CollectionExerciseState.CREATED);
    } catch (CTPException expectedException) {
      actualException = expectedException;
    }
    assertNotNull(actualException);
    assertEquals(
        "Collection exercise events must be set sequentially", actualException.getMessage());
  }

}