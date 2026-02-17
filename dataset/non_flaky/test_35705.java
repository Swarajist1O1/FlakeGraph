class DummyClass_35705 {
  @Test
  public void testGetLogNext() throws Exception {
    LoggingContext loggingContext = new WorkerLoggingContext("TFL_NS_1", "APP_1", "WORKER_1", "RUN1", "INSTANCE1");
    FileLogReader logReader = injector.getInstance(FileLogReader.class);
    LoggingTester tester = new LoggingTester();
    tester.testGetNext(logReader, loggingContext);
  }

}