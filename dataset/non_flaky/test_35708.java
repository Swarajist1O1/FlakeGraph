class DummyClass_35708 {
  @Test
  public void testCDAPLogAppender() {
    int syncInterval = 1024 * 1024;
    CDAPLogAppender cdapLogAppender = new CDAPLogAppender();

    cdapLogAppender.setSyncIntervalBytes(syncInterval);
    cdapLogAppender.setMaxFileLifetimeMs(TimeUnit.DAYS.toMillis(1));
    cdapLogAppender.setMaxFileSizeInBytes(104857600);
    cdapLogAppender.setDirPermissions("700");
    cdapLogAppender.setFilePermissions("600");
    cdapLogAppender.setFileRetentionDurationDays(1);
    cdapLogAppender.setLogCleanupIntervalMins(10);
    cdapLogAppender.setFileCleanupBatchSize(100);
    AppenderContext context = new LocalAppenderContext(injector.getInstance(TransactionRunner.class),
                                                       injector.getInstance(LocationFactory.class),
                                                       new NoOpMetricsCollectionService());
    context.start();
    cdapLogAppender.setContext(context);
    cdapLogAppender.start();

    FileMetaDataReader fileMetaDataReader = injector.getInstance(FileMetaDataReader.class);
    LoggingEvent event =
      new LoggingEvent("io.cdap.Test",
                       (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME),
                       Level.ERROR , "test message", null, null);
    Map<String, String> properties = new HashMap<>();
    properties.put(NamespaceLoggingContext.TAG_NAMESPACE_ID, "default");
    properties.put(ApplicationLoggingContext.TAG_APPLICATION_ID, "testApp");
    properties.put(UserServiceLoggingContext.TAG_USER_SERVICE_ID, "testService");

    event.setMDCPropertyMap(properties);

    cdapLogAppender.doAppend(event);
    cdapLogAppender.stop();
    context.stop();

    try {
      List<LogLocation> files = fileMetaDataReader.listFiles(cdapLogAppender.getLoggingPath(properties),
                                                             0, Long.MAX_VALUE);
      Assert.assertEquals(1, files.size());
      LogLocation logLocation = files.get(0);
      Assert.assertEquals(LogLocation.VERSION_1, logLocation.getFrameworkVersion());
      Assert.assertTrue(logLocation.getLocation().exists());
      CloseableIterator<LogEvent> logEventCloseableIterator =
        logLocation.readLog(Filter.EMPTY_FILTER, 0, Long.MAX_VALUE, Integer.MAX_VALUE);
      int logCount = 0;
      while (logEventCloseableIterator.hasNext()) {
        logCount++;
        LogEvent logEvent = logEventCloseableIterator.next();
        Assert.assertEquals(event.getMessage(), logEvent.getLoggingEvent().getMessage());
      }
      logEventCloseableIterator.close();
      Assert.assertEquals(1, logCount);
      // checking permission
      String expectedPermissions = "rw-------";
      for (LogLocation file : files) {
        Location location = file.getLocation();
        Assert.assertEquals(expectedPermissions, location.getPermissions());
      }
    } catch (Exception e) {
      Assert.fail();
    }
  }

}