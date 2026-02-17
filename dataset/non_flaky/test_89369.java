class DummyClass_89369 {
  @Test
  public void testReadIgnoreCheckpointV2WhenV1Enabled() throws InterruptedException {
    setupSystemFactory(config());
    CheckpointV1 checkpointV1 = buildCheckpointV1(INPUT_SSP0, "0");
    List<IncomingMessageEnvelope> checkpointEnvelopes =
        ImmutableList.of(newCheckpointV1Envelope(TASK0, checkpointV1, "0"),
            newCheckpointV2Envelope(TASK0, buildCheckpointV2(INPUT_SSP0, "1"), "1"));
    setupConsumer(checkpointEnvelopes);
    // default is to only read CheckpointV1
    KafkaCheckpointManager kafkaCheckpointManager = buildKafkaCheckpointManager(true, config());
    kafkaCheckpointManager.register(TASK0);
    Checkpoint actualCheckpoint = kafkaCheckpointManager.readLastCheckpoint(TASK0);
    assertEquals(checkpointV1, actualCheckpoint);
  }

}