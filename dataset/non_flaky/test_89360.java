class DummyClass_89360 {
  @Test
  public void testReadSucceedsOnKeySerdeExceptionsWhenValidationIsDisabled() throws InterruptedException {
    setupSystemFactory(config());
    List<IncomingMessageEnvelope> checkpointEnvelopes =
        ImmutableList.of(newCheckpointV1Envelope(TASK0, buildCheckpointV1(INPUT_SSP0, "0"), "0"));
    setupConsumer(checkpointEnvelopes);
    // wire up an exception throwing serde with the checkpointManager
    CheckpointV1Serde checkpointV1Serde = mock(CheckpointV1Serde.class);
    doThrow(new RuntimeException("serde failed")).when(checkpointV1Serde).fromBytes(any());
    KafkaCheckpointManager checkpointManager =
        new KafkaCheckpointManager(CHECKPOINT_SPEC, this.systemFactory, false, config(), this.metricsRegistry,
            checkpointV1Serde, CHECKPOINT_V2_SERDE, KAFKA_CHECKPOINT_LOG_KEY_SERDE);
    checkpointManager.register(TASK0);

    // expect the read to succeed in spite of the exception from ExceptionThrowingSerde
    assertNull(checkpointManager.readLastCheckpoint(TASK0));
  }

}