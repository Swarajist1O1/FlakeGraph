class DummyClass_35707 {
  @Test
  public void testGetLog() throws Exception {
    // LogReader.getLog is tested in LogSaverTest for distributed mode
    LoggingContext loggingContext = new WorkerLoggingContext("TFL_NS_1", "APP_1", "WORKER_1", "RUN1", "INSTANCE1");
    FileLogReader logTail = injector.getInstance(FileLogReader.class);
    LoggingTester.LogCallback logCallback1 = new LoggingTester.LogCallback();
    logTail.getLogPrev(loggingContext, ReadRange.LATEST, 60, Filter.EMPTY_FILTER,
                       logCallback1);
    List<LogEvent> allEvents = logCallback1.getEvents();
    Assert.assertEquals(60, allEvents.size());

    List<LogEvent> events =
      Lists.newArrayList(logTail.getLog(loggingContext, allEvents.get(10).getLoggingEvent().getTimeStamp(),
                                        allEvents.get(15).getLoggingEvent().getTimeStamp(), Filter.EMPTY_FILTER));

    Assert.assertEquals(5, events.size());
    Assert.assertEquals(allEvents.get(10).getLoggingEvent().getFormattedMessage(),
                        events.get(0).getLoggingEvent().getFormattedMessage());
    Assert.assertEquals(allEvents.get(14).getLoggingEvent().getFormattedMessage(),
                        events.get(4).getLoggingEvent().getFormattedMessage());


    events =
      Lists.newArrayList(logTail.getLog(loggingContext, allEvents.get(0).getLoggingEvent().getTimeStamp(),
                                        allEvents.get(59).getLoggingEvent().getTimeStamp(), Filter.EMPTY_FILTER));
    Assert.assertEquals(59, events.size());
    Assert.assertEquals(allEvents.get(0).getLoggingEvent().getFormattedMessage(),
                        events.get(0).getLoggingEvent().getFormattedMessage());
    Assert.assertEquals(allEvents.get(58).getLoggingEvent().getFormattedMessage(),
                        events.get(58).getLoggingEvent().getFormattedMessage());

    events =
      Lists.newArrayList(logTail.getLog(loggingContext, allEvents.get(12).getLoggingEvent().getTimeStamp(),
                                        allEvents.get(41).getLoggingEvent().getTimeStamp(), Filter.EMPTY_FILTER));
    Assert.assertEquals(29, events.size());
    Assert.assertEquals(allEvents.get(12).getLoggingEvent().getFormattedMessage(),
                        events.get(0).getLoggingEvent().getFormattedMessage());
    Assert.assertEquals(allEvents.get(40).getLoggingEvent().getFormattedMessage(),
                        events.get(28).getLoggingEvent().getFormattedMessage());

    events =
      Lists.newArrayList(logTail.getLog(loggingContext, allEvents.get(22).getLoggingEvent().getTimeStamp(),
                                        allEvents.get(38).getLoggingEvent().getTimeStamp(), Filter.EMPTY_FILTER));
    Assert.assertEquals(16, events.size());
    Assert.assertEquals(allEvents.get(22).getLoggingEvent().getFormattedMessage(),
                        events.get(0).getLoggingEvent().getFormattedMessage());
    Assert.assertEquals(allEvents.get(37).getLoggingEvent().getFormattedMessage(),
                        events.get(15).getLoggingEvent().getFormattedMessage());

    events =
      Lists.newArrayList(logTail.getLog(loggingContext, allEvents.get(41).getLoggingEvent().getTimeStamp(),
                                        allEvents.get(59).getLoggingEvent().getTimeStamp(), Filter.EMPTY_FILTER));
    Assert.assertEquals(18, events.size());
    Assert.assertEquals(allEvents.get(41).getLoggingEvent().getFormattedMessage(),
                        events.get(0).getLoggingEvent().getFormattedMessage());
    Assert.assertEquals(allEvents.get(58).getLoggingEvent().getFormattedMessage(),
                        events.get(17).getLoggingEvent().getFormattedMessage());

    // Try with null run id, should get all logs for WORKER_1
    LoggingContext loggingContext1 = new WorkerLoggingContext("TFL_NS_1", "APP_1", "WORKER_1", null, "INSTANCE1");
    events =
      Lists.newArrayList(logTail.getLog(loggingContext1, 0, Long.MAX_VALUE, Filter.EMPTY_FILTER));
    Assert.assertEquals(120, events.size());
  }

}