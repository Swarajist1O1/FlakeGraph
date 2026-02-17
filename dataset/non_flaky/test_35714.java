class DummyClass_35714 {
  @Test
  public void testDistributedLogPrevFile() throws Exception {
    ReadRange readRange = new ReadRange(0, Long.MAX_VALUE, LogOffset.INVALID_KAFKA_OFFSET);
    testDistributedLogPrev(readRange, LOGGING_CONTEXT_FILE, 7, 6, "TestDistributedLogReader Log message2 ", 40);

    readRange = new ReadRange(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1),
                                        System.currentTimeMillis(), LogOffset.INVALID_KAFKA_OFFSET);

    testDistributedLogPrev(readRange, LOGGING_CONTEXT_FILE, 7, 6, "TestDistributedLogReader Log message2 ", 40);

    testDistributedLogPrev(ReadRange.LATEST, LOGGING_CONTEXT_FILE, 7, 6, "TestDistributedLogReader Log message2 ", 40);
  }

}