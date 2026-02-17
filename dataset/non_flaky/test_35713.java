class DummyClass_35713 {
  @Test
  public void testDistributedLogNextBoth() throws Exception {
    ReadRange readRange = new ReadRange(0, Long.MAX_VALUE, LogOffset.INVALID_KAFKA_OFFSET);
    testDistributedLogNext(readRange, LOGGING_CONTEXT_BOTH, 20, 3, "TestDistributedLogReader Log message1 ", 60, 0);

    readRange = new ReadRange(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1),
                              System.currentTimeMillis(), LogOffset.INVALID_KAFKA_OFFSET);
    testDistributedLogNext(readRange, LOGGING_CONTEXT_BOTH, 20, 3, "TestDistributedLogReader Log message1 ", 60, 0);

    testDistributedLogNext(ReadRange.LATEST, LOGGING_CONTEXT_BOTH, 1, 3,
                           "TestDistributedLogReader Log message1 ", 3, 57);
  }

}