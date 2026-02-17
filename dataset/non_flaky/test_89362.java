class DummyClass_89362 {
  @Test
  public void testRegister() {
    setupSystemFactory(config());
    KafkaCheckpointManager kafkaCheckpointManager = buildKafkaCheckpointManager(true, config());
    kafkaCheckpointManager.register(TASK0);
    verify(this.systemProducer).register(TASK0.getTaskName());
  }

}