class DummyClass_35719 {
  @Test
  public void testGetPrev() throws Exception {
    LoggingContext loggingContext = new WorkerLoggingContext("TKL_NS_1", "APP_1", "FLOW_1", "RUN1", "INSTANCE1");
    KafkaLogReader logReader = KAFKA_TESTER.getInjector().getInstance(KafkaLogReader.class);
    LoggingTester tester = new LoggingTester();
    tester.testGetPrev(logReader, loggingContext);
  }

}