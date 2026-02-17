class DummyClass_89316 {
  @Test(expected = SamzaException.class)
  public void testGetSystemStreamPartitionCountsShouldTerminateAfterFiniteRetriesOnException() throws Exception {
    final Set<String> streamNames = ImmutableSet.of(VALID_TOPIC);
    final long cacheTTL = 100L;

    when(mockKafkaConsumer.partitionsFor(VALID_TOPIC)).thenThrow(new RuntimeException())
        .thenThrow(new RuntimeException())
        .thenThrow(new RuntimeException())
        .thenThrow(new RuntimeException())
        .thenThrow(new RuntimeException());

    kafkaSystemAdmin.getSystemStreamPartitionCounts(streamNames, cacheTTL);
  }

}