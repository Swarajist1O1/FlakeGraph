class DummyClass_89364 {
  @Test
  public void testWriteCheckpointShouldRecreateSystemProducerOnFailure() {
    setupSystemFactory(config());
    SystemProducer secondKafkaProducer = mock(SystemProducer.class);
    // override default mock behavior to return a second producer on the second call to create a producer
    when(this.systemFactory.getProducer(CHECKPOINT_SYSTEM, config(), this.metricsRegistry,
        KafkaCheckpointManager.class.getSimpleName())).thenReturn(this.systemProducer, secondKafkaProducer);
    // first producer throws an exception on flush
    doThrow(new RuntimeException("flush failed")).when(this.systemProducer).flush(TASK0.getTaskName());
    KafkaCheckpointManager kafkaCheckpointManager = buildKafkaCheckpointManager(true, config());
    kafkaCheckpointManager.register(TASK0);

    CheckpointV1 checkpointV1 = buildCheckpointV1(INPUT_SSP0, "0");
    kafkaCheckpointManager.writeCheckpoint(TASK0, checkpointV1);

    // first producer should be stopped
    verify(this.systemProducer).stop();
    // register and start the second producer
    verify(secondKafkaProducer).register(TASK0.getTaskName());
    verify(secondKafkaProducer).start();
    // check that the second producer was given the message to send out
    ArgumentCaptor<OutgoingMessageEnvelope> outgoingMessageEnvelopeArgumentCaptor =
        ArgumentCaptor.forClass(OutgoingMessageEnvelope.class);
    verify(secondKafkaProducer).send(eq(TASK0.getTaskName()), outgoingMessageEnvelopeArgumentCaptor.capture());
    assertEquals(CHECKPOINT_SSP, outgoingMessageEnvelopeArgumentCaptor.getValue().getSystemStream());
    assertEquals(new KafkaCheckpointLogKey(KafkaCheckpointLogKey.CHECKPOINT_V1_KEY_TYPE, TASK0, GROUPER_FACTORY_CLASS),
        KAFKA_CHECKPOINT_LOG_KEY_SERDE.fromBytes((byte[]) outgoingMessageEnvelopeArgumentCaptor.getValue().getKey()));
    assertEquals(checkpointV1,
        CHECKPOINT_V1_SERDE.fromBytes((byte[]) outgoingMessageEnvelopeArgumentCaptor.getValue().getMessage()));
    verify(secondKafkaProducer).flush(TASK0.getTaskName());
  }

}