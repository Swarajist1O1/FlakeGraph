class DummyClass_89315 {
  @Test(expected = SamzaException.class)
  public void testGetSystemStreamMetadataShouldTerminateAfterFiniteRetriesOnException() {
    when(mockKafkaConsumer.partitionsFor(VALID_TOPIC)).thenThrow(new RuntimeException())
        .thenThrow(new RuntimeException())
        .thenThrow(new RuntimeException())
        .thenThrow(new RuntimeException())
        .thenThrow(new RuntimeException());

    kafkaSystemAdmin.getSystemStreamMetadata(ImmutableSet.of(VALID_TOPIC));
  }

}