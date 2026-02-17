class DummyClass_89365 {
  @Test
  public void testCreateResources() {
    setupSystemFactory(config());
    KafkaCheckpointManager kafkaCheckpointManager = buildKafkaCheckpointManager(true, config());
    kafkaCheckpointManager.createResources();

    verify(this.createResourcesSystemAdmin).start();
    verify(this.createResourcesSystemAdmin).createStream(CHECKPOINT_SPEC);
    verify(this.createResourcesSystemAdmin).validateStream(CHECKPOINT_SPEC);
    verify(this.createResourcesSystemAdmin).stop();
  }

}