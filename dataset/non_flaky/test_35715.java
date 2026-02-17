class DummyClass_35715 {
  @Test
  public void testDistributedLogNextFile() throws Exception {
    ReadRange readRange = new ReadRange(0, Long.MAX_VALUE, LogOffset.INVALID_KAFKA_OFFSET);

    testDistributedLogNext(readRange, LOGGING_CONTEXT_FILE, 14, 3, "TestDistributedLogReader Log message2 ", 40, 0);

    readRange = new ReadRange(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1),
                              System.currentTimeMillis(), LogOffset.INVALID_KAFKA_OFFSET);
    testDistributedLogNext(readRange, LOGGING_CONTEXT_FILE, 14, 3, "TestDistributedLogReader Log message2 ", 40, 0);

    testDistributedLogNext(ReadRange.LATEST, LOGGING_CONTEXT_FILE, 1, 5,
                           "TestDistributedLogReader Log message2 ", 5, 35);
  }

}