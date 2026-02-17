class DummyClass_89368 {
  @Test
  public void testReadCheckpointV1() throws InterruptedException {
    setupSystemFactory(config());
    CheckpointV1 checkpointV1 = buildCheckpointV1(INPUT_SSP0, "0");
    List<IncomingMessageEnvelope> checkpointEnvelopes =
        ImmutableList.of(newCheckpointV1Envelope(TASK0, checkpointV1, "0"));
    setupConsumer(checkpointEnvelopes);
    KafkaCheckpointManager kafkaCheckpointManager = buildKafkaCheckpointManager(true, config());
    kafkaCheckpointManager.register(TASK0);
    Checkpoint actualCheckpoint = kafkaCheckpointManager.readLastCheckpoint(TASK0);
    assertEquals(checkpointV1, actualCheckpoint);
  }

}