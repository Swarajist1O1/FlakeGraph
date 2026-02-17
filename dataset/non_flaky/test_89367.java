class DummyClass_89367 {
  @Test
  public void testReadEmpty() throws InterruptedException {
    setupSystemFactory(config());
    setupConsumer(ImmutableList.of());
    KafkaCheckpointManager kafkaCheckpointManager = buildKafkaCheckpointManager(true, config());
    kafkaCheckpointManager.register(TASK0);
    assertNull(kafkaCheckpointManager.readLastCheckpoint(TASK0));
  }

}