class DummyClass_35717 {
  @Test
  public void testDistributedLogNextKafka() throws Exception {
    ReadRange readRange = new ReadRange(0, Long.MAX_VALUE, LogOffset.INVALID_KAFKA_OFFSET);
    testDistributedLogNext(readRange, LOGGING_CONTEXT_KAFKA, 10, 3, "TestDistributedLogReader Log message3 ", 30, 0);

    readRange = new ReadRange(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1),
                              System.currentTimeMillis(), LogOffset.INVALID_KAFKA_OFFSET);
    testDistributedLogNext(readRange, LOGGING_CONTEXT_KAFKA, 10, 3, "TestDistributedLogReader Log message3 ", 30, 0);

    testDistributedLogNext(ReadRange.LATEST, LOGGING_CONTEXT_KAFKA, 1, 8,
                           "TestDistributedLogReader Log message3 ", 8, 22);
  }

}