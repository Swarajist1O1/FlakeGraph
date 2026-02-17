class DummyClass_35718 {
  @Test
  public void testGetNext() throws Exception {
    // Check with null runId and null instanceId
    LoggingContext loggingContext = new WorkerLoggingContext("TKL_NS_1", "APP_1", "FLOW_1", "RUN1", "INSTANCE1");
    KafkaLogReader logReader = KAFKA_TESTER.getInjector().getInstance(KafkaLogReader.class);
    LoggingTester tester = new LoggingTester();
    tester.testGetNext(logReader, loggingContext);
  }

}