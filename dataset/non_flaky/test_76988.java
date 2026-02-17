class DummyClass_76988 {
  @Test
  public void rpcFailTest() throws Exception {
    String testAppId = "rpcFailTest";
    shuffleWriteClientImpl.registerShuffle(shuffleServerInfo1,
        testAppId, 0, Lists.newArrayList(new PartitionRange(0, 0)));
    Map<Long, byte[]> expectedData = Maps.newHashMap();
    Roaring64NavigableMap blockIdBitmap = Roaring64NavigableMap.bitmapOf();

    // simulator a failed server
    ShuffleServerInfo fakeShuffleServerInfo =
        new ShuffleServerInfo("127.0.0.1-20001", shuffleServers.get(0).getIp(), SHUFFLE_SERVER_PORT + 100);
    List<ShuffleBlockInfo> blocks = createShuffleBlockList(
        0, 0, 0, 3, 25, blockIdBitmap,
        expectedData, Lists.newArrayList(shuffleServerInfo1, fakeShuffleServerInfo));
    SendShuffleDataResult result = shuffleWriteClientImpl.sendShuffleData(testAppId, blocks);
    Roaring64NavigableMap failedBlockIdBitmap = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap succBlockIdBitmap = Roaring64NavigableMap.bitmapOf();
    for (Long blockId : result.getFailedBlockIds()) {
      failedBlockIdBitmap.addLong(blockId);
    }
    for (Long blockId : result.getSuccessBlockIds()) {
      succBlockIdBitmap.addLong(blockId);
    }
    assertEquals(blockIdBitmap, failedBlockIdBitmap);
    assertEquals(blockIdBitmap, succBlockIdBitmap);

    boolean commitResult = shuffleWriteClientImpl.sendCommit(Sets.newHashSet(
        shuffleServerInfo1, fakeShuffleServerInfo), testAppId, 0, 2);
    assertFalse(commitResult);

    Map<Integer, List<Long>> ptb = Maps.newHashMap();
    ptb.put(1, Lists.newArrayList(1L));
    try {
      Map<Integer, List<ShuffleServerInfo>> partitionToServers = Maps.newHashMap();
      partitionToServers.put(1, Lists.newArrayList(
          shuffleServerInfo1, fakeShuffleServerInfo));
      shuffleWriteClientImpl.reportShuffleResult(partitionToServers, testAppId, 0, 0, ptb, 2);
      fail(EXPECTED_EXCEPTION_MESSAGE);
    } catch (Exception e) {
      assertTrue(e.getMessage().contains("Report shuffle result is failed for"));
    }
  }

}