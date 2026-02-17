class DummyClass_89339 {
  @Test
  public void testStartpointSpecificOffsetVisitorShouldResolveToCorrectOffset() {
    final KafkaConsumer consumer = Mockito.mock(KafkaConsumer.class);
    final KafkaStartpointToOffsetResolver kafkaStartpointToOffsetResolver = new KafkaStartpointToOffsetResolver(consumer);

    final StartpointSpecific testStartpointSpecific = new StartpointSpecific(TEST_OFFSET);

    // Invoke the consumer with startpoint.
    String resolvedOffset = kafkaStartpointToOffsetResolver.visit(TEST_SYSTEM_STREAM_PARTITION, testStartpointSpecific);
    Assert.assertEquals(TEST_OFFSET, resolvedOffset);
  }

}