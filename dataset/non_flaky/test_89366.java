class DummyClass_89366 {
  @Test
  public void testCreateResourcesSkipValidation() {
    setupSystemFactory(config());
    KafkaCheckpointManager kafkaCheckpointManager = buildKafkaCheckpointManager(false, config());
    kafkaCheckpointManager.createResources();

    verify(this.createResourcesSystemAdmin).start();
    verify(this.createResourcesSystemAdmin).createStream(CHECKPOINT_SPEC);
    verify(this.createResourcesSystemAdmin, never()).validateStream(CHECKPOINT_SPEC);
    verify(this.createResourcesSystemAdmin).stop();
  }

}