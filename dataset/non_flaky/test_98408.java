class DummyClass_98408 {
  @Test
  public void testReferenceEndBeforeReferenceStartIsInvalid() {
    final Event refEnd = new Event();
    refEnd.setTag((Tag.ref_period_end.toString()));
    refEnd.setTimestamp(Timestamp.from(Instant.now().plus(1, ChronoUnit.DAYS)));
    final Event refStart = new Event();
    refStart.setTag((Tag.ref_period_start.toString()));
    refStart.setTimestamp(Timestamp.from(Instant.now().plus(2, ChronoUnit.DAYS)));
    final List<Event> events = new ArrayList<>();
    events.add(refEnd);
    CTPException actualException = null;
    try {
      referencePeriodValidator.validate(events, refStart, CollectionExerciseState.CREATED);
    } catch (CTPException expectedException) {
      actualException = expectedException;
    }
    assertNotNull(actualException);
    assertEquals(
        "Reference period end date must be after start date", actualException.getMessage());
  }

}