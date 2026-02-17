class DummyClass_89357 {
  @Test(expected = TopicAlreadyMarkedForDeletionException.class)
  public void testCreateResourcesTopicCreationError() {
    setupSystemFactory(config());
    // throw an exception during createStream
    doThrow(new TopicAlreadyMarkedForDeletionException("invalid stream")).when(this.createResourcesSystemAdmin)
        .createStream(CHECKPOINT_SPEC);
    KafkaCheckpointManager checkpointManager = buildKafkaCheckpointManager(true, config());
    // expect an exception during startup
    checkpointManager.createResources();
  }

}