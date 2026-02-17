class DummyClass_89372 {
  @Test
  public void testReadMultipleCheckpointsMultipleSSP() throws InterruptedException {
    setupSystemFactory(config());
    KafkaCheckpointManager checkpointManager = buildKafkaCheckpointManager(true, config());
    checkpointManager.register(TASK0);
    checkpointManager.register(TASK1);

    // mock out a consumer that returns 5 checkpoint IMEs for each SSP
    int newestOffset = 5;
    int checkpointOffsetCounter = 0;
    List<List<IncomingMessageEnvelope>> pollOutputs = new ArrayList<>();
    for (int offset = 1; offset <= newestOffset; offset++) {
      pollOutputs.add(ImmutableList.of(
          // use regular offset value for INPUT_SSP0
          newCheckpointV1Envelope(TASK0, buildCheckpointV1(INPUT_SSP0, Integer.toString(offset)),
              Integer.toString(checkpointOffsetCounter++)),
          // use (offset * 2) value for INPUT_SSP1 so offsets are different from INPUT_SSP0
          newCheckpointV1Envelope(TASK1, buildCheckpointV1(INPUT_SSP1, Integer.toString(offset * 2)),
              Integer.toString(checkpointOffsetCounter++))));
    }
    setupConsumerMultiplePoll(pollOutputs);

    assertEquals(buildCheckpointV1(INPUT_SSP0, Integer.toString(newestOffset)),
        checkpointManager.readLastCheckpoint(TASK0));
    assertEquals(buildCheckpointV1(INPUT_SSP1, Integer.toString(newestOffset * 2)),
        checkpointManager.readLastCheckpoint(TASK1));
    // check expected number of polls (+1 is for the final empty poll), and the checkpoint is the newest message
    verify(this.systemConsumer, times(newestOffset + 1)).poll(ImmutableSet.of(CHECKPOINT_SSP),
        SystemConsumer.BLOCK_ON_OUTSTANDING_MESSAGES);
  }

}