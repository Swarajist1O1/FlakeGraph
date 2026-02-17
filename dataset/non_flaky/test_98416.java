class DummyClass_98416 {
  @Test
  public void testNudgeAfterReturnByEndInvalid() {
    final Event goLive = new Event();
    goLive.setTag((EventService.Tag.go_live.toString()));
    goLive.setTimestamp(Timestamp.from(Instant.now()));

    final Event nudgeEvent = new Event();
    nudgeEvent.setTag(EventService.Tag.nudge_email_0.toString());
    nudgeEvent.setTimestamp(Timestamp.from(Instant.now().plus(4, ChronoUnit.DAYS)));

    final Event returnBy = new Event();
    returnBy.setTag((EventService.Tag.return_by.toString()));
    returnBy.setTimestamp(Timestamp.from(Instant.now().plus(3, ChronoUnit.DAYS)));

    final List<Event> events = Arrays.asList(goLive, returnBy);
    CTPException actualException = null;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    sdf.setTimeZone(TimeZone.getTimeZone("Europe/London"));
    Date goLiveDate = new Date(goLive.getTimestamp().getTime());
    Date returnByDate = new Date(returnBy.getTimestamp().getTime());

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