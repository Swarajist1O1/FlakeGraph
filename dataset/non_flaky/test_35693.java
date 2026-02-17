class DummyClass_35693 {
  @Test
  public void testFramework() throws Exception {
    DistributedLogFramework framework = injector.getInstance(DistributedLogFramework.class);
    CConfiguration cConf = injector.getInstance(CConfiguration.class);

    framework.startAndWait();

    // Send some logs to Kafka.
    LoggingContext context = new ServiceLoggingContext(NamespaceId.SYSTEM.getNamespace(),
                                                       Constants.Logging.COMPONENT_NAME,
                                                       "test");

    // Make sure all events get flushed in the same batch
    long eventTimeBase = System.currentTimeMillis() + cConf.getInt(Constants.Logging.PIPELINE_EVENT_DELAY_MS);
    final int msgCount = 50;
    for (int i = 0; i < msgCount; i++) {
      // Publish logs in descending timestamp order
      publishLog(
        cConf.get(Constants.Logging.KAFKA_TOPIC), context,
        ImmutableList.of(
          createLoggingEvent("io.cdap.test." + i, Level.INFO, "Testing " + i, eventTimeBase - i)
        )
      );
    }

    // Read the logs back. They should be sorted by timestamp order.
    final FileMetaDataReader metaDataReader = injector.getInstance(FileMetaDataReader.class);
    Tasks.waitFor(true, () -> {
      List<LogLocation> locations = metaDataReader.listFiles(new LogPathIdentifier(NamespaceId.SYSTEM.getNamespace(),
                                                                                    Constants.Logging.COMPONENT_NAME,
                                                                                   "test"), 0, Long.MAX_VALUE);
      if (locations.size() != 1) {
        return false;
      }
      LogLocation location = locations.get(0);
      int i = 0;
      try {
        try (CloseableIterator<LogEvent> iter = location.readLog(Filter.EMPTY_FILTER, 0, Long.MAX_VALUE, msgCount)) {
          while (iter.hasNext()) {
            String expectedMsg = "Testing " + (msgCount - i - 1);
            LogEvent event = iter.next();
            if (!expectedMsg.equals(event.getLoggingEvent().getMessage())) {
              return false;
            }
            i++;
          }
          return i == msgCount;
        }
      } catch (Exception e) {
        // It's possible the file is an invalid Avro file due to a race between creation of the meta data
        // and the time when actual content are flushed to the file
        return false;
      }
    }, 10, TimeUnit.SECONDS, msgCount, TimeUnit.MILLISECONDS);

    framework.stopAndWait();

    String kafkaTopic = cConf.get(Constants.Logging.KAFKA_TOPIC);
    // Check the checkpoint is persisted correctly. Since all messages are processed,
    // the checkpoint should be the same as the message count.
    CheckpointManager<KafkaOffset> checkpointManager = getCheckpointManager(kafkaTopic);
    Checkpoint<KafkaOffset> checkpoint = checkpointManager.getCheckpoint(0);
    Assert.assertEquals(msgCount, checkpoint.getOffset().getNextOffset());
  }

}