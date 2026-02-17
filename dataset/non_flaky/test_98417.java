class DummyClass_98417 {
  @Test
  public void testNudgeBeforeGoliveInvalid() {
    final Event goLive = new Event();
    goLive.setTag((EventService.Tag.go_live.toString()));
    goLive.setTimestamp(Timestamp.from(Instant.now().plus(2, ChronoUnit.DAYS)));
    final Event returnBy = new Event();
    returnBy.setTag((EventService.Tag.return_by.toString()));
    returnBy.setTimestamp(Timestamp.from(Instant.now().plus(3, ChronoUnit.DAYS)));
    final List<Event> events = Arrays.asList(goLive, returnBy);

    final Event nudgeEvent = new Event();
    nudgeEvent.setTag(EventService.Tag.nudge_email_0.toString());
    nudgeEvent.setTimestamp(Timestamp.from(Instant.now().plus(1, ChronoUnit.DAYS)));

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    sdf.setTimeZone(TimeZone.getTimeZone("Europe/London"));
    Date goLiveDate = new Date(goLive.getTimestamp().getTime());
    Date returnByDate = new Date(returnBy.getTimestamp().getTime());

    CTPException actualException = null;
    try {
      nudgeEmailValidator.validate(
          events, nudgeEvent, CollectionExerciseDTO.CollectionExerciseState.SCHEDULED);
    } catch (CTPException expectedException) {
      actualException = expectedException;
    }
    assertNotNull(actualException);
    String expectedMessage =
        "Nudge email must be set after the Go Live date ("
            + sdf.format(goLiveDate)
            + ") "
            + "and before Return by date ("
            + sdf.format(returnByDate)
            + ")";
    assertEquals(expectedMessage, actualException.getMessage());
  }

}