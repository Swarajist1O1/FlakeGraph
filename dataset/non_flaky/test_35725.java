class DummyClass_35725 {
  @Test
  public void testTmsLogAppender() throws Exception {
    // setup TMSLogAppender and log messages to it
    LogAppenderInitializer logAppenderInitializer = new LogAppenderInitializer(tmsLogAppender);
    logAppenderInitializer.initialize("TestTMSLogging");

    Logger logger = LoggerFactory.getLogger("TestTMSLogging");
    LoggingTester loggingTester = new LoggingTester();

    LoggingContext loggingContext = new MapReduceLoggingContext("TKL_NS_1", "APP_1", "MR_1", "RUN1");
    loggingTester.generateLogs(logger, loggingContext);

    logAppenderInitializer.close();

    // fetch and deserialize all the logs from TMS
    LoggingEventSerializer loggingEventSerializer = new LoggingEventSerializer();

    Map<Integer, List<ILoggingEvent>> partitionedFetchedLogs = new HashMap<>();
    int totalFetchedLogs = 0;

    for (Map.Entry<Integer, TopicId> topicId : topicIds.entrySet()) {
      List<ILoggingEvent> fetchedLogs = new ArrayList<>();
      MessageFetcher messageFetcher = client.prepareFetch(topicId.getValue());
      try (CloseableIterator<RawMessage> messages = messageFetcher.fetch()) {
        while (messages.hasNext()) {
          RawMessage message = messages.next();
          ILoggingEvent iLoggingEvent = loggingEventSerializer.fromBytes(ByteBuffer.wrap(message.getPayload()));
          fetchedLogs.add(iLoggingEvent);
        }
      }

      totalFetchedLogs += fetchedLogs.size();
      partitionedFetchedLogs.put(topicId.getKey(), fetchedLogs);
    }

    // LoggingTester emits 240 logs in total
    Assert.assertEquals(240, totalFetchedLogs);

    // Read the partition that our LoggingContext maps to and filter the logs in there to the logs that correspond
    // to our LoggingContext.
    LogPartitionType logPartitionType =
            LogPartitionType.valueOf(cConf.get(Constants.Logging.LOG_PUBLISH_PARTITION_KEY).toUpperCase());
    String partitionKey = logPartitionType.getPartitionKey(loggingContext);
    int partition = TMSLogAppender.partition(partitionKey, cConf.getInt(Constants.Logging.NUM_PARTITIONS));
    Filter logFilter = LoggingContextHelper.createFilter(loggingContext);

    List<ILoggingEvent> filteredLogs =
            partitionedFetchedLogs.get(partition).stream().filter(logFilter::match).collect(Collectors.toList());

    // LoggingTester emits 60 logs with the given LoggingContext
    Assert.assertEquals(60, filteredLogs.size());

    for (int i = 0; i < filteredLogs.size(); i++) {
      ILoggingEvent loggingEvent = filteredLogs.get(i);
      Assert.assertEquals(String.format("Test log message %s arg1 arg2", i), loggingEvent.getFormattedMessage());
    }
  }

}