class DummyClass_35711 {
  @Test
  public void testLogFileManager() throws Exception {
    int syncInterval = 1024 * 1024;
    long maxLifeTimeMs = 50;
    long maxFileSizeInBytes = 104857600;
    FileMetaDataWriter fileMetaDataWriter = new FileMetaDataWriter(injector.getInstance(TransactionRunner.class));
    LogFileManager logFileManager = new LogFileManager("700", "600", maxLifeTimeMs, maxFileSizeInBytes, syncInterval,
                                                       fileMetaDataWriter,
                                                       injector.getInstance(LocationFactory.class));
    LogPathIdentifier logPathIdentifier = new LogPathIdentifier("test", "testApp", "testFlow");
    long timestamp = System.currentTimeMillis();
    LogFileOutputStream outputStream = logFileManager.getLogFileOutputStream(logPathIdentifier, timestamp);
    LoggingEvent event1 =
      getLoggingEvent("io.cdap.Test1",
                      (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME),
                      Level.ERROR , "test message 1");
    outputStream.append(event1);
    // we are doing this, instead of calling getLogFileOutputStream to avoid race, if test machine can be slow.
    Assert.assertNotNull((logFileManager.getActiveOutputStream(logPathIdentifier)));
    TimeUnit.MILLISECONDS.sleep(60);
    logFileManager.flush();
    // should be closed on flush, should return null
    Assert.assertNull((logFileManager.getActiveOutputStream(logPathIdentifier)));
    LogFileOutputStream newLogOutStream = logFileManager.getLogFileOutputStream(logPathIdentifier, timestamp);
    // make sure the new location we got is different
    Assert.assertNotEquals(outputStream.getLocation(), newLogOutStream.getLocation());
  }

}