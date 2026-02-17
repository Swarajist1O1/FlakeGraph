class DummyClass_35706 {
  @Test
  public void testGetLogPrev() throws Exception {
    LoggingContext loggingContext = new WorkerLoggingContext("TFL_NS_1", "APP_1", "WORKER_1", "RUN1", "INSTANCE1");
    FileLogReader logReader = injector.getInstance(FileLogReader.class);
    LoggingTester tester = new LoggingTester();
    tester.testGetPrev(logReader, loggingContext);
  }

}