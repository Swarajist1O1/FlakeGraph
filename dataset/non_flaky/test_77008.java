class DummyClass_77008 {
  @Test
  public void rpcMetricsTest() {
    String appId = "rpcMetricsTest";
    int shuffleId = 0;
    double oldGrpcTotal = shuffleServers.get(0).getGrpcMetrics().getCounterGrpcTotal().get();
    double oldValue = shuffleServers.get(0).getGrpcMetrics().getCounterMap().
        get(ShuffleServerGrpcMetrics.REGISTER_SHUFFLE_METHOD).get();
    shuffleServerClient.registerShuffle(new RssRegisterShuffleRequest(appId, shuffleId,
        Lists.newArrayList(new PartitionRange(0, 1))));
    double newValue = shuffleServers.get(0).getGrpcMetrics().getCounterMap()
        .get(ShuffleServerGrpcMetrics.REGISTER_SHUFFLE_METHOD).get();
    assertEquals(oldValue + 1, newValue, 0.5);
    assertEquals(0,
        shuffleServers.get(0).getGrpcMetrics().getGaugeMap().get(
            ShuffleServerGrpcMetrics.REGISTER_SHUFFLE_METHOD).get(), 0.5);

    oldValue = shuffleServers.get(0).getGrpcMetrics().getCounterMap().get(
        ShuffleServerGrpcMetrics.APP_HEARTBEAT_METHOD).get();
    shuffleServerClient.sendHeartBeat(new RssAppHeartBeatRequest(appId, 10000));
    newValue = shuffleServers.get(0).getGrpcMetrics().getCounterMap().get(
        ShuffleServerGrpcMetrics.APP_HEARTBEAT_METHOD).get();
    assertEquals(oldValue + 1, newValue, 0.5);
    assertEquals(0,
        shuffleServers.get(0).getGrpcMetrics().getGaugeMap().get(
            ShuffleServerGrpcMetrics.APP_HEARTBEAT_METHOD).get(), 0.5);

    oldValue = shuffleServers.get(0).getGrpcMetrics().getCounterMap().get(
        ShuffleServerGrpcMetrics.REQUIRE_BUFFER_METHOD).get();
    shuffleServerClient.requirePreAllocation(100, 10, 1000);
    newValue = shuffleServers.get(0).getGrpcMetrics().getCounterMap().get(
        ShuffleServerGrpcMetrics.REQUIRE_BUFFER_METHOD).get();
    assertEquals(oldValue + 1, newValue, 0.5);
    assertEquals(0,
        shuffleServers.get(0).getGrpcMetrics().getGaugeMap().get(
            ShuffleServerGrpcMetrics.REQUIRE_BUFFER_METHOD).get(), 0.5);

    oldValue = shuffleServers.get(0).getGrpcMetrics().getCounterMap().get(
        ShuffleServerGrpcMetrics.SEND_SHUFFLE_DATA_METHOD).get();
    List<ShuffleBlockInfo> blockInfos = Lists.newArrayList(new ShuffleBlockInfo(shuffleId, 0, 0, 100, 0,
        new byte[]{}, Lists.newArrayList(), 0, 100, 0));
    Map<Integer, List<ShuffleBlockInfo>> partitionToBlocks = Maps.newHashMap();
    partitionToBlocks.put(0, blockInfos);
    Map<Integer, Map<Integer, List<ShuffleBlockInfo>>> shuffleToBlocks = Maps.newHashMap();
    shuffleToBlocks.put(0, partitionToBlocks);
    RssSendShuffleDataRequest rssdr = new RssSendShuffleDataRequest(
        appId, 3, 1000, shuffleToBlocks);
    shuffleServerClient.sendShuffleData(rssdr);
    newValue = shuffleServers.get(0).getGrpcMetrics().getCounterMap().get(
        ShuffleServerGrpcMetrics.SEND_SHUFFLE_DATA_METHOD).get();
    assertEquals(oldValue + 1, newValue, 0.5);
    assertEquals(0,
        shuffleServers.get(0).getGrpcMetrics().getGaugeMap().get(
            ShuffleServerGrpcMetrics.SEND_SHUFFLE_DATA_METHOD).get(), 0.5);

    oldValue = shuffleServers.get(0).getGrpcMetrics().getCounterMap().get(
        ShuffleServerGrpcMetrics.COMMIT_SHUFFLE_TASK_METHOD).get();
    shuffleServerClient.sendCommit(new RssSendCommitRequest(appId, shuffleId));
    newValue = shuffleServers.get(0).getGrpcMetrics().getCounterMap().get(
        ShuffleServerGrpcMetrics.COMMIT_SHUFFLE_TASK_METHOD).get();
    assertEquals(oldValue + 1, newValue, 0.5);
    assertEquals(0,
        shuffleServers.get(0).getGrpcMetrics().getGaugeMap().get(
            ShuffleServerGrpcMetrics.COMMIT_SHUFFLE_TASK_METHOD).get(), 0.5);

    oldValue = shuffleServers.get(0).getGrpcMetrics().getCounterMap().get(
        ShuffleServerGrpcMetrics.FINISH_SHUFFLE_METHOD).get();
    shuffleServerClient.finishShuffle(new RssFinishShuffleRequest(appId, shuffleId));
    newValue = shuffleServers.get(0).getGrpcMetrics().getCounterMap().get(
        ShuffleServerGrpcMetrics.FINISH_SHUFFLE_METHOD).get();
    assertEquals(oldValue + 1, newValue, 0.5);
    assertEquals(0,
        shuffleServers.get(0).getGrpcMetrics().getGaugeMap().get(
            ShuffleServerGrpcMetrics.FINISH_SHUFFLE_METHOD).get(), 0.5);

    oldValue = shuffleServers.get(0).getGrpcMetrics().getCounterMap().get(
        ShuffleServerGrpcMetrics.REPORT_SHUFFLE_RESULT_METHOD).get();
    Map<Integer, List<Long>> partitionToBlockIds = Maps.newHashMap();
    List<Long> blockIds1 = getBlockIdList(1, 3);
    List<Long> blockIds2 = getBlockIdList(2, 2);
    List<Long> blockIds3 = getBlockIdList(3, 1);
    partitionToBlockIds.put(1, blockIds1);
    partitionToBlockIds.put(2, blockIds2);
    partitionToBlockIds.put(3, blockIds3);
    RssReportShuffleResultRequest request =
        new RssReportShuffleResultRequest(appId, shuffleId, 0L, partitionToBlockIds, 1);
    shuffleServerClient.reportShuffleResult(request);
    newValue = shuffleServers.get(0).getGrpcMetrics().getCounterMap().get(
        ShuffleServerGrpcMetrics.REPORT_SHUFFLE_RESULT_METHOD).get();
    assertEquals(oldValue + 1, newValue, 0.5);
    assertEquals(0,
        shuffleServers.get(0).getGrpcMetrics().getGaugeMap().get(
            ShuffleServerGrpcMetrics.REPORT_SHUFFLE_RESULT_METHOD).get(), 0.5);

    oldValue = shuffleServers.get(0).getGrpcMetrics().getCounterMap().get(
        ShuffleServerGrpcMetrics.GET_SHUFFLE_RESULT_METHOD).get();
    shuffleServerClient.getShuffleResult(new RssGetShuffleResultRequest(appId, shuffleId, 1));
    newValue = shuffleServers.get(0).getGrpcMetrics().getCounterMap().get(
        ShuffleServerGrpcMetrics.GET_SHUFFLE_RESULT_METHOD).get();
    assertEquals(oldValue + 1, newValue, 0.5);
    assertEquals(0,
        shuffleServers.get(0).getGrpcMetrics().getGaugeMap().get(
            ShuffleServerGrpcMetrics.GET_SHUFFLE_RESULT_METHOD).get(), 0.5);

    oldValue = shuffleServers.get(0).getGrpcMetrics().getCounterMap().get(
        ShuffleServerGrpcMetrics.GET_SHUFFLE_INDEX_METHOD).get();
    try {
      shuffleServerClient.getShuffleIndex(new RssGetShuffleIndexRequest(
          appId, shuffleId, 1, 1, 3));
    } catch (Exception e) {
      // ignore the exception, just test metrics value
    }
    newValue = shuffleServers.get(0).getGrpcMetrics().getCounterMap().get(
        ShuffleServerGrpcMetrics.GET_SHUFFLE_INDEX_METHOD).get();
    assertEquals(oldValue + 1, newValue, 0.5);
    assertEquals(0,
        shuffleServers.get(0).getGrpcMetrics().getGaugeMap().get(
            ShuffleServerGrpcMetrics.GET_SHUFFLE_INDEX_METHOD).get(), 0.5);

    oldValue = shuffleServers.get(0).getGrpcMetrics().getCounterMap().get(
        ShuffleServerGrpcMetrics.GET_SHUFFLE_DATA_METHOD).get();
    try {
      shuffleServerClient.getShuffleData(new RssGetShuffleDataRequest(
          appId, shuffleId, 0, 1, 3,
          0, 100));
    } catch (Exception e) {
      // ignore the exception, just test metrics value
    }
    newValue = shuffleServers.get(0).getGrpcMetrics().getCounterMap().get(
        ShuffleServerGrpcMetrics.GET_SHUFFLE_DATA_METHOD).get();
    assertEquals(oldValue + 1, newValue, 0.5);
    assertEquals(0,
        shuffleServers.get(0).getGrpcMetrics().getGaugeMap().get(
            ShuffleServerGrpcMetrics.GET_SHUFFLE_DATA_METHOD).get(), 0.5);

    double newGrpcTotal = shuffleServers.get(0).getGrpcMetrics().getCounterGrpcTotal().get();
    // require buffer will be called one more time when send data
    assertEquals(oldGrpcTotal + 11, newGrpcTotal, 0.5);
    assertEquals(0, shuffleServers.get(0).getGrpcMetrics().getGaugeGrpcOpen().get(), 0.5);
  }

}