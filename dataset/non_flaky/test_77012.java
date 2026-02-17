class DummyClass_77012 {
  @Test
  public void diskUsageTest() {
    String appId = "app_read_diskusage_data";
    long originSize = shuffleServers.get(0).getShuffleBufferManager().getCapacity();
    Map<Long, byte[]> expectedData = Maps.newHashMap();

    RssRegisterShuffleRequest rr1 =  new RssRegisterShuffleRequest(appId, 2,
        Lists.newArrayList(new PartitionRange(0, 0)));
    shuffleServerClient.registerShuffle(rr1);

    RssRegisterShuffleRequest rr2 =  new RssRegisterShuffleRequest(appId, 3,
        Lists.newArrayList(new PartitionRange(1, 1)));
    shuffleServerClient.registerShuffle(rr2);

    RssRegisterShuffleRequest rr3 =  new RssRegisterShuffleRequest(appId, 2,
        Lists.newArrayList(new PartitionRange(1, 1)));
    shuffleServerClient.registerShuffle(rr3);

    Roaring64NavigableMap blockIdBitmap1 = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap blockIdBitmap2 = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap blockIdBitmap3 = Roaring64NavigableMap.bitmapOf();

    List<ShuffleBlockInfo> blocks1 = createShuffleBlockList(
        2, 0, 1,30, 10 * 1024 * 1024, blockIdBitmap1, expectedData);

    List<ShuffleBlockInfo> blocks2 = createShuffleBlockList(
        3, 1, 2,9, 10 * 1024 * 1024, blockIdBitmap2, expectedData);

    List<ShuffleBlockInfo> blocks3 = createShuffleBlockList(
        2, 1, 2,9, 10 * 1024 * 1024, blockIdBitmap3, expectedData);

    DiskItem item = shuffleServers.get(0).getMultiStorageManager().getDiskItem(appId, 2, 0);
    item.createMetadataIfNotExist(appId + "/" + 2);
    item.getLock(appId + "/" + 2).readLock().lock();
    sendSinglePartitionToShuffleServer(appId, 2, 0, 1, blocks1);
    assertFalse(item.canWrite());
    assertEquals(30 * 1024 * 1024 * 10, item.getNotUploadedSize(appId + "/" + 2));
    assertEquals(1, item.getNotUploadedPartitions(appId + "/" + 2).getCardinality());
    boolean isException = false;
    try {
      sendSinglePartitionToShuffleServer(appId, 2, 1, 2, blocks3);
    } catch (RuntimeException re) {
      isException = true;
      assertTrue(re.getMessage().contains("Can't finish shuffle process"));
    }
    item.getLock(appId + "/" + 2).readLock().unlock();
    Uninterruptibles.sleepUninterruptibly(6, TimeUnit.SECONDS);
    assertEquals(originSize, shuffleServers.get(0).getShuffleBufferManager().getCapacity());
    assertTrue(isException);
    RssGetShuffleResultRequest rg1 = new RssGetShuffleResultRequest(appId, 2, 0);
    shuffleServerClient.getShuffleResult(rg1);
    validateResult(appId, 2, 0, expectedData, Sets.newHashSet());
    ShuffleReadClientImpl readClient = new ShuffleReadClientImpl("LOCALFILE_AND_HDFS",
        appId, 2, 0, 100, 1, 10, 1000, HDFS_URI + "rss/multi_storage",
        blockIdBitmap1, Roaring64NavigableMap.bitmapOf(1), Lists.newArrayList(new ShuffleServerInfo("test", LOCALHOST, SHUFFLE_SERVER_PORT)), conf);
    validateResult(readClient, expectedData, blockIdBitmap1);
    try {
      sendSinglePartitionToShuffleServer(appId, 3, 1,2, blocks2);
    } catch (RuntimeException re) {
      fail();
    }
    RssGetShuffleResultRequest rg2 = new RssGetShuffleResultRequest(appId, 3, 1);
    shuffleServerClient.getShuffleResult(rg2);
    validateResult(appId, 3, 1, expectedData,
        getExpectBlockIds(blocks2));

    Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
  }

}