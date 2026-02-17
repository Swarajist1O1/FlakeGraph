class DummyClass_89340 {
  @Test
  public void testStartpointTimestampVisitorShouldResolveToCorrectOffset() {
    // Define dummy variables for testing.
    final Long testTimeStamp = 10L;

    final KafkaConsumer consumer = Mockito.mock(KafkaConsumer.class);

    final KafkaStartpointToOffsetResolver kafkaStartpointToOffsetResolver = new KafkaStartpointToOffsetResolver(consumer);

    final StartpointTimestamp startpointTimestamp = new StartpointTimestamp(testTimeStamp);
    final Map<TopicPartition, OffsetAndTimestamp> offsetForTimesResult = ImmutableMap.of(
        TEST_TOPIC_PARTITION, new OffsetAndTimestamp(Long.valueOf(TEST_OFFSET), testTimeStamp));

    // Mock the consumer interactions.
    Mockito.when(consumer.offsetsForTimes(ImmutableMap.of(TEST_TOPIC_PARTITION, testTimeStamp))).thenReturn(offsetForTimesResult);
    Mockito.when(consumer.position(TEST_TOPIC_PARTITION)).thenReturn(Long.valueOf(TEST_OFFSET));

    String resolvedOffset = kafkaStartpointToOffsetResolver.visit(TEST_SYSTEM_STREAM_PARTITION, startpointTimestamp);
    Assert.assertEquals(TEST_OFFSET, resolvedOffset);
  }

}