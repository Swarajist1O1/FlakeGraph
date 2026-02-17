class DummyClass_89373 {
  @Test
  public void testReadMultipleCheckpointsUpgradeCheckpointVersion() throws InterruptedException {
    Config config = config(ImmutableMap.of(TaskConfig.CHECKPOINT_READ_VERSIONS, "2,1"));
    setupSystemFactory(config);
    KafkaCheckpointManager kafkaCheckpointManager = buildKafkaCheckpointManager(true, config);
    kafkaCheckpointManager.register(TASK0);
    kafkaCheckpointManager.register(TASK1);

    List<IncomingMessageEnvelope> checkpointEnvelopesV1 =
        ImmutableList.of(newCheckpointV1Envelope(TASK0, buildCheckpointV1(INPUT_SSP0, "0"), "0"),
            newCheckpointV1Envelope(TASK1, buildCheckpointV1(INPUT_SSP1, "0"), "1"));
    CheckpointV2 ssp0CheckpointV2 = buildCheckpointV2(INPUT_SSP0, "10");
    CheckpointV2 ssp1CheckpointV2 = buildCheckpointV2(INPUT_SSP1, "11");
    List<IncomingMessageEnvelope> checkpointEnvelopesV2 =
        ImmutableList.of(newCheckpointV2Envelope(TASK0, ssp0CheckpointV2, "2"),
            newCheckpointV2Envelope(TASK1, ssp1CheckpointV2, "3"));
    setupConsumerMultiplePoll(ImmutableList.of(checkpointEnvelopesV1, checkpointEnvelopesV2));
    assertEquals(ssp0CheckpointV2, kafkaCheckpointManager.readLastCheckpoint(TASK0));
    assertEquals(ssp1CheckpointV2, kafkaCheckpointManager.readLastCheckpoint(TASK1));
    // 2 polls for actual checkpoints, 1 final empty poll
    verify(this.systemConsumer, times(3)).poll(ImmutableSet.of(CHECKPOINT_SSP),
        SystemConsumer.BLOCK_ON_OUTSTANDING_MESSAGES);
  }

}