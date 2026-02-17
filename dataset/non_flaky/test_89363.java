class DummyClass_89363 {
  @Test
  public void testStop() {
    setupSystemFactory(config());
    KafkaCheckpointManager checkpointManager = buildKafkaCheckpointManager(true, config());
    checkpointManager.stop();
    verify(this.systemProducer).stop();
    // default configuration for stopConsumerAfterFirstRead means that consumer is not stopped here
    verify(this.systemConsumer, never()).stop();
    verify(this.systemAdmin).stop();
  }

}