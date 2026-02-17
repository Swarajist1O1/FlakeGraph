class DummyClass_35716 {
  @Test
  public void testDistributedLogPrevKafka() throws Exception {
    ReadRange readRange = new ReadRange(0, Long.MAX_VALUE, LogOffset.INVALID_KAFKA_OFFSET);
    testDistributedLogPrev(readRange, LOGGING_CONTEXT_KAFKA, 5, 6, "TestDistributedLogReader Log message3 ", 30);

    readRange = new ReadRange(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1),
                                        System.currentTimeMillis(), LogOffset.INVALID_KAFKA_OFFSET);

    testDistributedLogPrev(readRange, LOGGING_CONTEXT_KAFKA, 5, 6, "TestDistributedLogReader Log message3 ", 30);

    testDistributedLogPrev(ReadRange.LATEST, LOGGING_CONTEXT_KAFKA, 5, 6, "TestDistributedLogReader Log message3 ", 30);
  }

}