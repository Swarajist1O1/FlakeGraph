class DummyClass_98395 {
  @Test
  public void testMandatoryEventsCannotBeChangedIfCollectionExerciseIsExecuted() {
    final List<Event> events = new ArrayList<>();

    final Event mpsEvent = new Event();
    mpsEvent.setTag((Tag.mps.toString()));
    mpsEvent.setTimestamp(Timestamp.from(Instant.now().plus(1, ChronoUnit.MINUTES)));

    CTPException actualException = null;
    try {
      mandatoryValidator.validate(events, mpsEvent, CollectionExerciseState.EXECUTED);
    } catch (CTPException expectedException) {
      actualException = expectedException;
    }
    assertNotNull(actualException);
    assertEquals(
        "Mandatory events cannot be changed if collection exercise is set to live, executed, validated or locked",
        actualException.getMessage());
  }

}