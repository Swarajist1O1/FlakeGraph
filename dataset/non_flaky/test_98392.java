class DummyClass_98392 {
  @Test
  public void testMandatoryEventsCannotBeChangedIfCollectionExerciseIsValidated() {
    final List<Event> events = new ArrayList<>();

    final Event mpsEvent = new Event();
    mpsEvent.setTag((Tag.mps.toString()));
    mpsEvent.setTimestamp(Timestamp.from(Instant.now().plus(1, ChronoUnit.MINUTES)));

    CTPException actualException = null;
    try {
      mandatoryValidator.validate(events, mpsEvent, CollectionExerciseState.VALIDATED);
    } catch (CTPException expectedException) {
      actualException = expectedException;
    }
    assertNotNull(actualException);
    assertEquals(
        "Mandatory events cannot be changed if collection exercise is set to live, executed, validated or locked",
        actualException.getMessage());
  }

}