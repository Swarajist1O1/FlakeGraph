class DummyClass_35684 {
  @Test
  public void testInOrderEvents() throws InterruptedException, IOException {
    String topic = "testInOrderEvents";
    KafkaPipelineConfig config = new KafkaPipelineConfig(topic, Collections.singleton(0), 1024L, EVENT_DELAY_MILLIS,
                                                         1048576, 200L);
    KAFKA_TESTER.createTopic(topic, 1);

    // Publish log messages to Kafka and wait for all messages to be published
    long baseTime = System.currentTimeMillis() - EVENT_DELAY_MILLIS;
    List<ILoggingEvent> inOrderEvents = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      inOrderEvents.add(createLoggingEvent("test.logger", Level.INFO, Integer.toString(i), baseTime + i));
    }
    publishLog(topic, inOrderEvents);
    waitForAllLogsPublished(topic, inOrderEvents.size());

    KafkaOffsetResolver offsetResolver = new KafkaOffsetResolver(KAFKA_TESTER.getBrokerService(), config);
    // Use every event's timestamp as target time and assert that found offset is the next offset of the current offset
    for (int i = 0; i < inOrderEvents.size(); i++) {
      long targetTime = inOrderEvents.get(i).getTimeStamp();
      long offset = offsetResolver.getStartOffset(Long.MAX_VALUE, targetTime, 0);
      Assert.assertEquals("Failed to find the expected event with the target time: " + targetTime,
                          i + 1, offset);
    }
  }

}