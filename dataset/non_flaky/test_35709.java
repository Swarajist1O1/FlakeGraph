class DummyClass_35709 {
  @Test
  public void testCDAPLogAppenderRotation() throws Exception {
    int syncInterval = 1024 * 1024;
    FileMetaDataReader fileMetaDataReader = injector.getInstance(FileMetaDataReader.class);
    CDAPLogAppender cdapLogAppender = new CDAPLogAppender();
    AppenderContext context = new LocalAppenderContext(injector.getInstance(TransactionRunner.class),
                                                       injector.getInstance(LocationFactory.class),
                                                       new NoOpMetricsCollectionService());
    context.start();

    cdapLogAppender.setSyncIntervalBytes(syncInterval);
    cdapLogAppender.setMaxFileLifetimeMs(500);
    cdapLogAppender.setMaxFileSizeInBytes(104857600);
    cdapLogAppender.setDirPermissions("750");
    cdapLogAppender.setFilePermissions("640");
    cdapLogAppender.setFileRetentionDurationDays(1);
    cdapLogAppender.setLogCleanupIntervalMins(10);
    cdapLogAppender.setFileCleanupBatchSize(100);
    cdapLogAppender.setContext(context);
    cdapLogAppender.start();

    Map<String, String> properties = new HashMap<>();
    properties.put(NamespaceLoggingContext.TAG_NAMESPACE_ID, "testTimeRotation");
    properties.put(ApplicationLoggingContext.TAG_APPLICATION_ID, "testApp");
    properties.put(UserServiceLoggingContext.TAG_USER_SERVICE_ID, "testService");

    long currentTimeMillisEvent1 = System.currentTimeMillis();

    LoggingEvent event1 =
      getLoggingEvent("io.cdap.Test1",
                      (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME),
                      Level.ERROR , "test message 1", properties);

    event1.setTimeStamp(currentTimeMillisEvent1);
    cdapLogAppender.doAppend(event1);

    // Pause pass the max file lifetime ms
    TimeUnit.MILLISECONDS.sleep(500);

    long currentTimeMillisEvent2 = System.currentTimeMillis();

    LoggingEvent event2 = getLoggingEvent("io.cdap.Test2",
                                          (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(
                                            Logger.ROOT_LOGGER_NAME), Level.ERROR , "test message 2", properties);
    event2.setTimeStamp(currentTimeMillisEvent1 + 1000);
    cdapLogAppender.doAppend(event2);
    cdapLogAppender.stop();
    context.stop();

    try {
      List<LogLocation> files = fileMetaDataReader.listFiles(cdapLogAppender.getLoggingPath(properties),
                                                             0, Long.MAX_VALUE);
      Assert.assertEquals(2, files.size());
      assertLogEventDetails(event1, files.get(0));
      assertLogEventDetails(event2, files.get(1));
      Assert.assertEquals(currentTimeMillisEvent1, files.get(0).getEventTimeMs());
      Assert.assertEquals(currentTimeMillisEvent1 + 1000, files.get(1).getEventTimeMs());
      Assert.assertTrue(files.get(0).getFileCreationTimeMs() >= currentTimeMillisEvent1);
      Assert.assertTrue(files.get(1).getFileCreationTimeMs() >= currentTimeMillisEvent2);

      // checking permission
      String expectedPermissions = "rw-r-----";
      for (LogLocation file : files) {
        Location location = file.getLocation();
        Assert.assertEquals(expectedPermissions, location.getPermissions());
      }
    } catch (Exception e) {
      Assert.fail();
    }
  }

}