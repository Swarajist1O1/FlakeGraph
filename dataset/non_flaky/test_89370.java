class DummyClass_89370 {
  @Test
  public void testReadCheckpointV2() throws InterruptedException {
    Config config = config(ImmutableMap.of(TaskConfig.CHECKPOINT_READ_VERSIONS, "1,2"));
    setupSystemFactory(config);
    CheckpointV2 checkpointV2 = buildCheckpointV2(INPUT_SSP0, "0");
    List<IncomingMessageEnvelope> checkpointEnvelopes =
        ImmutableList.of(newCheckpointV2Envelope(TASK0, checkpointV2, "0"));
    setupConsumer(checkpointEnvelopes);
    KafkaCheckpointManager kafkaCheckpointManager = buildKafkaCheckpointManager(true, config);
    kafkaCheckpointManager.register(TASK0);
    Checkpoint actualCheckpoint = kafkaCheckpointManager.readLastCheckpoint(TASK0);
    assertEquals(checkpointV2, actualCheckpoint);
  }

}