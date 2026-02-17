class DummyClass_35712 {
  @Test
  public void testDistributedLogPrevBoth() throws Exception {
    ReadRange readRange = new ReadRange(0, Long.MAX_VALUE, LogOffset.INVALID_KAFKA_OFFSET);
    testDistributedLogPrev(readRange, LOGGING_CONTEXT_BOTH, 16, 4, "TestDistributedLogReader Log message1 ", 60);

    readRange = new ReadRange(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1),
                                        System.currentTimeMillis(), LogOffset.INVALID_KAFKA_OFFSET);
    testDistributedLogPrev(readRange, LOGGING_CONTEXT_BOTH, 16, 4, "TestDistributedLogReader Log message1 ", 60);

    testDistributedLogPrev(ReadRange.LATEST, LOGGING_CONTEXT_BOTH, 9, 8, "TestDistributedLogReader Log message1 ", 60);
  }

}