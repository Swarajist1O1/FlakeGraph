class DummyClass_89358 {
  @Test(expected = StreamValidationException.class)
  public void testCreateResourcesTopicValidationError() {
    setupSystemFactory(config());
    // throw an exception during validateStream
    doThrow(new StreamValidationException("invalid stream")).when(this.createResourcesSystemAdmin)
        .validateStream(CHECKPOINT_SPEC);
    KafkaCheckpointManager checkpointManager = buildKafkaCheckpointManager(true, config());
    // expect an exception during startup
    checkpointManager.createResources();
  }

}