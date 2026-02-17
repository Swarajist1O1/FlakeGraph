class DummyClass_89342 {
  @Test
  public void testStartpointOldestVisitorShouldResolveToCorrectOffset() {
    // Define dummy variables for testing.
    final KafkaConsumer consumer = Mockito.mock(KafkaConsumer.class);
    final KafkaStartpointToOffsetResolver kafkaStartpointToOffsetResolver = new KafkaStartpointToOffsetResolver(consumer);

    final StartpointOldest testStartpointSpecific = new StartpointOldest();

    // Mock the consumer interactions.
    Mockito.when(consumer.beginningOffsets(ImmutableSet.of(TEST_TOPIC_PARTITION))).thenReturn(ImmutableMap.of(TEST_TOPIC_PARTITION, 10L));

    // Invoke the consumer with startpoint.
    String resolvedOffset = kafkaStartpointToOffsetResolver.visit(TEST_SYSTEM_STREAM_PARTITION, testStartpointSpecific);
    Assert.assertEquals(TEST_OFFSET, resolvedOffset);
  }

}