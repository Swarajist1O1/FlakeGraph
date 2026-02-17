class DummyClass_35683 {
  @Test
  public void testOutOfOrderEvents() throws Exception {
    String topic = "testOutOfOrderEvents";
    KafkaPipelineConfig config = new KafkaPipelineConfig(topic, Collections.singleton(0), 1024L, EVENT_DELAY_MILLIS,
                                                         1048576, 200L);
    KAFKA_TESTER.createTopic(topic, 1);

    // Publish log messages to Kafka and wait for all messages to be published
    long baseTime = System.currentTimeMillis() - 2 * EVENT_DELAY_MILLIS;
    List<ILoggingEvent> outOfOrderEvents = ImmutableList.of(
      createLoggingEvent("test.logger", Level.INFO, "0", baseTime - 20 * 1000 - EVENT_DELAY_MILLIS),
      createLoggingEvent("test.logger", Level.INFO, "0", baseTime - 20 * 1000 - EVENT_DELAY_MILLIS),
      createLoggingEvent("test.logger", Level.INFO, "1", baseTime - 7 * 1000 - EVENT_DELAY_MILLIS),
      createLoggingEvent("test.logger", Level.INFO, "2", baseTime - 9 * 100),
      createLoggingEvent("test.logger", Level.INFO, "3", baseTime - 500),
      createLoggingEvent("test.logger", Level.INFO, "1", baseTime - 9 * 1000),
      createLoggingEvent("test.logger", Level.INFO, "1", baseTime - 9 * 1000 + EVENT_DELAY_MILLIS / 2),
      createLoggingEvent("test.logger", Level.INFO, "1", baseTime - 9 * 1000),
      createLoggingEvent("test.logger", Level.INFO, "1", baseTime - 9 * 1000 - EVENT_DELAY_MILLIS / 2),
      createLoggingEvent("test.logger", Level.INFO, "1", baseTime - 10 * 1000),
      createLoggingEvent("test.logger", Level.INFO, "1", baseTime - 600),
      createLoggingEvent("test.logger", Level.INFO, "5", baseTime - 20 * 1000),
      createLoggingEvent("test.logger", Level.INFO, "5", baseTime - 20 * 1000 + EVENT_DELAY_MILLIS / 2),
      createLoggingEvent("test.logger", Level.INFO, "6", baseTime - 600),
      createLoggingEvent("test.logger", Level.INFO, "6", baseTime - 10 * 1000),
      createLoggingEvent("test.logger", Level.INFO, "7", baseTime - 2 * 1000 + EVENT_DELAY_MILLIS),
      createLoggingEvent("test.logger", Level.INFO, "8", baseTime - 7 * 1000 + EVENT_DELAY_MILLIS),
      createLoggingEvent("test.logger", Level.INFO, "4", baseTime - 100 + EVENT_DELAY_MILLIS));
    publishLog(topic, outOfOrderEvents);
    waitForAllLogsPublished(topic, outOfOrderEvents.size());

    KafkaOffsetResolver offsetResolver = new KafkaOffsetResolver(KAFKA_TESTER.getBrokerService(), config);
    // Use every event's timestamp as target time and assert that found offset with target timestamp
    // matches the expected offset
    for (ILoggingEvent event : outOfOrderEvents) {
      assertOffsetResolverResult(offsetResolver, outOfOrderEvents, event.getTimeStamp(), baseTime);
    }

    // Try to find the offset with an event time that has timestamp earlier than all event timestamps in Kafka
    assertOffsetResolverResult(offsetResolver, outOfOrderEvents,
                               baseTime - 10 * EVENT_DELAY_MILLIS, baseTime);

    // Try to find the offset with an event time that has timestamp larger than all event timestamps in Kafka
    assertOffsetResolverResult(offsetResolver, outOfOrderEvents,
                               baseTime + 10 * EVENT_DELAY_MILLIS, baseTime);

    // Use a random number between (timestamp - EVENT_DELAY_MILLIS, timestamp + EVENT_DELAY_MILLIS) as target time
    // and assert that found offset with target timestamp matches the expected offset
    for (int i = 0; i < 10; i++) {
      for (ILoggingEvent event : outOfOrderEvents) {
        assertOffsetResolverResult(offsetResolver, outOfOrderEvents,
                                   event.getTimeStamp() + RANDOM.nextInt() % EVENT_DELAY_MILLIS, baseTime);
      }
    }
  }

}