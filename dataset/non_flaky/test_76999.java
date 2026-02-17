class DummyClass_76999 {
  @Test
  public void hdfsWriteReadTest() {
    String appId = "app_hdfs_read_write";
    String dataBasePath = HDFS_URI + "rss/test";
    RssRegisterShuffleRequest rrsr = new RssRegisterShuffleRequest(appId, 0,
        Lists.newArrayList(new PartitionRange(0, 1)));
    shuffleServerClient.registerShuffle(rrsr);
    rrsr = new RssRegisterShuffleRequest(appId, 0, Lists.newArrayList(new PartitionRange(2, 3)));
    shuffleServerClient.registerShuffle(rrsr);

    Roaring64NavigableMap[] bitmaps = new Roaring64NavigableMap[4];
    Map<Long, byte[]> expectedData = Maps.newHashMap();
    Map<Integer, List<ShuffleBlockInfo>>  dataBlocks = createTestData(bitmaps, expectedData);
    Map<Integer, List<ShuffleBlockInfo>> partitionToBlocks = Maps.newHashMap();
    partitionToBlocks.put(0, dataBlocks.get(0));
    partitionToBlocks.put(1, dataBlocks.get(1));

    Map<Integer, Map<Integer, List<ShuffleBlockInfo>>> shuffleToBlocks = Maps.newHashMap();
    shuffleToBlocks.put(0, partitionToBlocks);

    RssSendShuffleDataRequest rssdr = new RssSendShuffleDataRequest(appId, 3, 1000, shuffleToBlocks);
    shuffleServerClient.sendShuffleData(rssdr);
    assertEquals(456, shuffleServers.get(0).getShuffleBufferManager().getUsedMemory());
    assertEquals(0, shuffleServers.get(0).getShuffleBufferManager().getPreAllocatedSize());
    RssSendCommitRequest rscr = new RssSendCommitRequest(appId, 0);
    shuffleServerClient.sendCommit(rscr);
    RssFinishShuffleRequest rfsr = new RssFinishShuffleRequest(appId, 0);

    ShuffleReadClientImpl readClient = new ShuffleReadClientImpl(StorageType.HDFS.name(),
        appId, 0, 0, 100, 2, 10, 1000,
        dataBasePath, bitmaps[0], Roaring64NavigableMap.bitmapOf(0), Lists.newArrayList(), new Configuration());
    assertNull(readClient.readShuffleBlockData());
    shuffleServerClient.finishShuffle(rfsr);

    partitionToBlocks.clear();
    partitionToBlocks.put(2, dataBlocks.get(2));
    shuffleToBlocks.clear();
    shuffleToBlocks.put(0, partitionToBlocks);
    rssdr = new RssSendShuffleDataRequest(appId, 3, 1000, shuffleToBlocks);
    shuffleServerClient.sendShuffleData(rssdr);
    assertEquals(0, shuffleServers.get(0).getShuffleBufferManager().getPreAllocatedSize());
    rscr = new RssSendCommitRequest(appId, 0);
    shuffleServerClient.sendCommit(rscr);
    rfsr = new RssFinishShuffleRequest(appId, 0);
    shuffleServerClient.finishShuffle(rfsr);

    partitionToBlocks.clear();
    partitionToBlocks.put(3, dataBlocks.get(3));
    shuffleToBlocks.clear();
    shuffleToBlocks.put(0, partitionToBlocks);
    rssdr = new RssSendShuffleDataRequest(appId, 3, 1000, shuffleToBlocks);
    shuffleServerClient.sendShuffleData(rssdr);
    rscr = new RssSendCommitRequest(appId, 0);
    shuffleServerClient.sendCommit(rscr);
    rfsr = new RssFinishShuffleRequest(appId, 0);
    shuffleServerClient.finishShuffle(rfsr);

    readClient = new ShuffleReadClientImpl(StorageType.HDFS.name(),
        appId, 0, 0, 100, 2, 10, 1000,
        dataBasePath, bitmaps[0], Roaring64NavigableMap.bitmapOf(0), Lists.newArrayList(), new Configuration());
    validateResult(readClient, expectedData, bitmaps[0]);

    readClient = new ShuffleReadClientImpl(StorageType.HDFS.name(),
        appId, 0, 1, 100, 2, 10, 1000,
        dataBasePath, bitmaps[1], Roaring64NavigableMap.bitmapOf(1), Lists.newArrayList(), new Configuration());
    validateResult(readClient, expectedData, bitmaps[1]);

    readClient = new ShuffleReadClientImpl(StorageType.HDFS.name(),
        appId, 0, 2, 100, 2, 10, 1000,
        dataBasePath, bitmaps[2], Roaring64NavigableMap.bitmapOf(2), Lists.newArrayList(), new Configuration());
    validateResult(readClient, expectedData, bitmaps[2]);

    readClient = new ShuffleReadClientImpl(StorageType.HDFS.name(),
        appId, 0, 3, 100, 2, 10, 1000,
        dataBasePath, bitmaps[3], Roaring64NavigableMap.bitmapOf(3), Lists.newArrayList(), new Configuration());
    validateResult(readClient, expectedData, bitmaps[3]);
  }

}