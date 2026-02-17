class DummyClass_89371 {
  @Test
  public void testReadCheckpointPriority() throws InterruptedException {
    Config config = config(ImmutableMap.of(TaskConfig.CHECKPOINT_READ_VERSIONS, "2,1"));
    setupSystemFactory(config);
    CheckpointV2 checkpointV2 = buildCheckpointV2(INPUT_SSP0, "1");
    List<IncomingMessageEnvelope> checkpointEnvelopes =
        ImmutableList.of(newCheckpointV1Envelope(TASK0, buildCheckpointV1(INPUT_SSP0, "0"), "0"),
            newCheckpointV2Envelope(TASK0, checkpointV2, "1"));
    setupConsumer(checkpointEnvelopes);
    KafkaCheckpointManager kafkaCheckpointManager = buildKafkaCheckpointManager(true, config);
    kafkaCheckpointManager.register(TASK0);
    Checkpoint actualCheckpoint = kafkaCheckpointManager.readLastCheckpoint(TASK0);
    assertEquals(checkpointV2, actualCheckpoint);
  }

}