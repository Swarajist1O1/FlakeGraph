class DummyClass_89341 {
  @Test
  public void testStartpointTimestampVisitorShouldResolveToCorrectOffsetWhenTimestampDoesNotExist() {
    final KafkaConsumer consumer = Mockito.mock(KafkaConsumer.class);
    final KafkaStartpointToOffsetResolver kafkaStartpointToOffsetResolver = new KafkaStartpointToOffsetResolver(consumer);

    final StartpointTimestamp startpointTimestamp = new StartpointTimestamp(0L);
    final Map<TopicPartition, OffsetAndTimestamp> offsetForTimesResult = new HashMap<>();
    offsetForTimesResult.put(TEST_TOPIC_PARTITION, null);

    // Mock the consumer interactions.
    Mockito.when(consumer.offsetsForTimes(ImmutableMap.of(TEST_TOPIC_PARTITION, 0L))).thenReturn(offsetForTimesResult);
    Mockito.when(consumer.endOffsets(ImmutableSet.of(TEST_TOPIC_PARTITION))).thenReturn(ImmutableMap.of(TEST_TOPIC_PARTITION, 10L));

    String resolvedOffset = kafkaStartpointToOffsetResolver.visit(TEST_SYSTEM_STREAM_PARTITION, startpointTimestamp);
    Assert.assertEquals(TEST_OFFSET, resolvedOffset);

    // Mock verifications.
    Mockito.verify(consumer).offsetsForTimes(ImmutableMap.of(TEST_TOPIC_PARTITION, 0L));
  }

}