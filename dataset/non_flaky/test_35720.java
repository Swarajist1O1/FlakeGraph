class DummyClass_35720 {
  @Test
  public void testPartitionKey() throws Exception {
    CConfiguration cConf = KAFKA_TESTER.getCConf();
    // set kafka partition key to application
    cConf.set(Constants.Logging.LOG_PUBLISH_PARTITION_KEY, "application");

    Logger logger = LoggerFactory.getLogger("TestKafkaLogging");
    LoggingContext loggingContext = new WorkerLoggingContext("TKL_NS_2", "APP_2", "FLOW_2", "RUN2", "INSTANCE2");
    LoggingContextAccessor.setLoggingContext(loggingContext);
    for (int i = 0; i < 40; ++i) {
      logger.warn("TKL_NS_2 Test log message {} {} {}", i, "arg1", "arg2", new Exception("test exception"));
    }

    loggingContext = new WorkerLoggingContext("TKL_NS_2", "APP_2", "FLOW_3", "RUN3", "INSTANCE3");
    LoggingContextAccessor.setLoggingContext(loggingContext);
    for (int i = 0; i < 40; ++i) {
      logger.warn("TKL_NS_2 Test log message {} {} {}", i, "arg1", "arg2", new Exception("test exception"));
    }

    final Multimap<Integer, String> actual = ArrayListMultimap.create();

    KAFKA_TESTER.getPublishedMessages(KAFKA_TESTER.getCConf().get(Constants.Logging.KAFKA_TOPIC),
                                      ImmutableSet.of(0, 1), 40, new Function<FetchedMessage, String>() {
        @Override
        public String apply(final FetchedMessage input) {
          try {
            Map.Entry<Integer, String> entry = convertFetchedMessage(input);
            actual.put(entry.getKey(), entry.getValue());
          } catch (IOException e) {
            // should never happen
          }
          return "";
        }

}