class DummyClass_89361 {
  @Test
  public void testStart() {
    setupSystemFactory(config());
    String oldestOffset = "1";
    String newestOffset = "2";
    SystemStreamMetadata checkpointTopicMetadata = new SystemStreamMetadata(CHECKPOINT_TOPIC,
        ImmutableMap.of(new Partition(0), new SystemStreamPartitionMetadata(oldestOffset, newestOffset,
            Integer.toString(Integer.parseInt(newestOffset) + 1))));
    when(this.systemAdmin.getSystemStreamMetadata(Collections.singleton(CHECKPOINT_TOPIC))).thenReturn(
        ImmutableMap.of(CHECKPOINT_TOPIC, checkpointTopicMetadata));

    KafkaCheckpointManager checkpointManager = buildKafkaCheckpointManager(true, config());

    checkpointManager.start();

    verify(this.systemProducer).start();
    verify(this.systemAdmin).start();
    verify(this.systemConsumer).register(CHECKPOINT_SSP, oldestOffset);
    verify(this.systemConsumer).start();
  }

}