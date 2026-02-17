class DummyClass_77002 {
  @Test
  public void localWriteReadTest() throws Exception {
    String testAppId = "localWriteReadTest";
    RssRegisterShuffleRequest rrsr = new RssRegisterShuffleRequest(testAppId, 0,
        Lists.newArrayList(new PartitionRange(0, 1)));
    shuffleServerClient.registerShuffle(rrsr);
    rrsr = new RssRegisterShuffleRequest(testAppId, 0, Lists.newArrayList(new PartitionRange(2, 3)));
    shuffleServerClient.registerShuffle(rrsr);

    Map<Long, byte[]> expectedData = Maps.newHashMap();

    Roaring64NavigableMap[] bitmaps = new Roaring64NavigableMap[4];
    Map<Integer, List<ShuffleBlockInfo>> partitionToBlocks = createTestData(bitmaps, expectedData);

    Set<Long> expectedBlockIds1 = transBitmapToSet(bitmaps[0]);
    Set<Long> expectedBlockIds2 = transBitmapToSet(bitmaps[1]);
    Set<Long> expectedBlockIds3 = transBitmapToSet(bitmaps[2]);
    Set<Long> expectedBlockIds4 = transBitmapToSet(bitmaps[3]);

    Map<Integer, Map<Integer, List<ShuffleBlockInfo>>> shuffleToBlocks = Maps.newHashMap();
    shuffleToBlocks.put(0, partitionToBlocks);

    RssSendShuffleDataRequest rssdr = new RssSendShuffleDataRequest(
        testAppId, 3, 1000, shuffleToBlocks);
    shuffleServerClient.sendShuffleData(rssdr);
    RssSendCommitRequest rscr = new RssSendCommitRequest(testAppId, 0);
    shuffleServerClient.sendCommit(rscr);
    RssFinishShuffleRequest rfsr = new RssFinishShuffleRequest(testAppId, 0);
    shuffleServerClient.finishShuffle(rfsr);

    ShuffleDataResult sdr  = readShuffleData(
        shuffleServerClient, testAppId, 0, 0, 2,
        10, 1000, 0);
    validateResult(sdr, expectedBlockIds1, expectedData, 0);
    sdr  = readShuffleData(
        shuffleServerClient, testAppId, 0, 1, 2,
        10, 1000, 0);
    validateResult(sdr, expectedBlockIds2, expectedData, 1);
    sdr  = readShuffleData(
        shuffleServerClient, testAppId, 0, 2, 2,
        10, 1000, 0);
    validateResult(sdr, expectedBlockIds3, expectedData, 2);
    sdr  = readShuffleData(
        shuffleServerClient, testAppId, 0, 3, 2,
        10, 1000, 0);
    validateResult(sdr, expectedBlockIds4, expectedData, 3);

    assertEquals(4, shuffleServers.get(0).getShuffleTaskManager()
        .getServerReadHandlers().get(testAppId).size());
    assertNotNull(shuffleServers.get(0).getShuffleTaskManager()
        .getPartitionsToBlockIds().get(testAppId));
    Thread.sleep(8000);
    assertNull(shuffleServers.get(0).getShuffleTaskManager().getServerReadHandlers().get(testAppId));
    assertNull(shuffleServers.get(0).getShuffleTaskManager().getPartitionsToBlockIds().get(testAppId));
  }

}