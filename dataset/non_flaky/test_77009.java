class DummyClass_77009 {
  @Test
  public void readUploadedDataTest() {
    String appId = "ap_read_uploaded_data";
    RssRegisterShuffleRequest rr1 =  new RssRegisterShuffleRequest(appId, 0,
        Lists.newArrayList(new PartitionRange(0, 0)));
    RssRegisterShuffleRequest rr2 =  new RssRegisterShuffleRequest(appId, 0,
        Lists.newArrayList(new PartitionRange(1, 1)));
    RssRegisterShuffleRequest rr3 =  new RssRegisterShuffleRequest(appId, 0,
        Lists.newArrayList(new PartitionRange(2, 2)));
    RssRegisterShuffleRequest rr4 =  new RssRegisterShuffleRequest(appId, 0,
        Lists.newArrayList(new PartitionRange(4, 4)));
    shuffleServerClient.registerShuffle(rr1);
    shuffleServerClient.registerShuffle(rr2);
    shuffleServerClient.registerShuffle(rr3);
    shuffleServerClient.registerShuffle(rr4);

    Map<Long, byte[]> expectedData = Maps.newHashMap();
    Set<Long> expectedBlock1 = Sets.newHashSet();
    Set<Long> expectedBlock2 = Sets.newHashSet();

    Roaring64NavigableMap blockIdBitmap1 = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap blockIdBitmap2 = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap blockIdBitmap3 = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap blockIdBitmap4 = Roaring64NavigableMap.bitmapOf();

    List<ShuffleBlockInfo> blocks1 = createShuffleBlockList(
        0, 0, 1,3, 25, blockIdBitmap1, expectedData);
    List<ShuffleBlockInfo> blocks2 = createShuffleBlockList(
        0, 1, 1,5,1024 * 1024, blockIdBitmap2, expectedData);
    List<ShuffleBlockInfo> blocks3 = createShuffleBlockList(
        0, 2, 2,4, 25, blockIdBitmap3, expectedData);
    List<ShuffleBlockInfo> blocks4 = createShuffleBlockList(
        0, 4, 3,1, 1024 * 1024, blockIdBitmap4, expectedData);


    blocks1.forEach(b -> expectedBlock1.add(b.getBlockId()));
    blocks2.forEach(b -> expectedBlock2.add(b.getBlockId()));

    Map<Integer, List<ShuffleBlockInfo>> partitionToBlocks = Maps.newHashMap();
    partitionToBlocks.put(0, blocks1);
    partitionToBlocks.put(1, blocks2);
    Map<Integer, Map<Integer, List<ShuffleBlockInfo>>> shuffleToBlocks = Maps.newHashMap();
    shuffleToBlocks.put(0, partitionToBlocks);
    RssSendShuffleDataRequest rs1 = new RssSendShuffleDataRequest(appId, 3, 1000, shuffleToBlocks);
    shuffleServerClient.sendShuffleData(rs1);

    RssSendCommitRequest rc1 = new RssSendCommitRequest(appId, 0);
    shuffleServerClient.sendCommit(rc1);
    RssFinishShuffleRequest rf1 = new RssFinishShuffleRequest(appId, 0);
    shuffleServerClient.finishShuffle(rf1);
    Map<Integer, List<Long>> partitionToBlockIds = Maps.newHashMap();
    partitionToBlockIds.put(0, new ArrayList<>(expectedBlock1));
    partitionToBlockIds.put(1, new ArrayList<>(expectedBlock2));
    RssReportShuffleResultRequest rrp1 = new RssReportShuffleResultRequest(
        appId, 0, 1L, partitionToBlockIds, 2);
    shuffleServerClient.reportShuffleResult(rrp1);

    DiskItem item = shuffleServers.get(0).getMultiStorageManager().getDiskItem(appId, 0, 0);
    assertTrue(item.canWrite());
    assertEquals(3 * 25, item.getNotUploadedSize(appId + "/" + 0));
    item = shuffleServers.get(0).getMultiStorageManager().getDiskItem(appId, 0, 1);
    assertTrue(item.canWrite());
    assertEquals(5 * 1024 * 1024, item.getNotUploadedSize(appId + "/" + 0));

    sendSinglePartitionToShuffleServer(appId, 0,2, 2L, blocks3);
    sendSinglePartitionToShuffleServer(appId, 0, 4, 3L, blocks4);

    item = shuffleServers.get(0).getMultiStorageManager().getDiskItem(appId, 0, 2);
    assertTrue(item.canWrite());
    assertEquals(3 * 25 + 4 * 25, item.getNotUploadedSize(appId + "/" + 0));

    item = shuffleServers.get(0).getMultiStorageManager().getDiskItem(appId, 0, 4);
    assertTrue(item.canWrite());
    assertEquals(5 * 1024 * 1024 + 1024 * 1024, item.getNotUploadedSize(appId + "/" + 0));


    RssGetShuffleResultRequest rg1 = new RssGetShuffleResultRequest(appId, 0, 0);
    shuffleServerClient.getShuffleResult(rg1);
    RssGetShuffleResultRequest rg2 = new RssGetShuffleResultRequest(appId, 0, 1);
    shuffleServerClient.getShuffleResult(rg2);
    RssGetShuffleResultRequest rg3 = new RssGetShuffleResultRequest(appId, 0, 2);
    shuffleServerClient.getShuffleResult(rg3);
    RssGetShuffleResultRequest rg4 = new RssGetShuffleResultRequest(appId, 0, 4);
    shuffleServerClient.getShuffleResult(rg4);

    readShuffleData(shuffleServerClient, appId, 0, 0, 1, 10, 100, 0);
    readShuffleData(shuffleServerClient, appId, 0, 1, 1, 10, 100, 0);


    wait(appId);

    item = shuffleServers.get(0).getMultiStorageManager().getDiskItem(appId, 0, 0);
    assertTrue(item.canWrite());
    assertEquals(0, item.getNotUploadedSize(appId + "/" + 0));

    item = shuffleServers.get(0).getMultiStorageManager().getDiskItem(appId, 0, 1);
    assertTrue(item.canWrite());
    assertEquals(0, item.getNotUploadedSize(appId + "/" + 0));

    boolean isException = false;
    try {
      ShuffleDataResult result = readShuffleData(shuffleServerClient, appId, 0, 0,
          1, 10, 1000,  0);
    } catch (RuntimeException re) {
      isException = true;
      assertTrue(re.getMessage().contains("Can't get shuffle index"));
    }
    assertTrue(isException);

    ShuffleReadClientImpl readClient = new ShuffleReadClientImpl("LOCALFILE_AND_HDFS",
        appId, 0, 0, 100, 1, 10, 1000, HDFS_URI + "rss/multi_storage",
        blockIdBitmap1, Roaring64NavigableMap.bitmapOf(1), Lists.newArrayList(), conf);
    validateResult(readClient, expectedData, blockIdBitmap1);

    readClient = new ShuffleReadClientImpl("LOCALFILE_AND_HDFS",
        appId, 0, 1, 100, 1, 10, 1000, HDFS_URI + "rss/multi_storage",
        blockIdBitmap2, Roaring64NavigableMap.bitmapOf(1), Lists.newArrayList(), conf);
    validateResult(readClient, expectedData, blockIdBitmap2);

    readClient = new ShuffleReadClientImpl("LOCALFILE_AND_HDFS",
        appId, 0, 2, 100, 1, 10, 1000, HDFS_URI + "rss/multi_storage",
        blockIdBitmap3, Roaring64NavigableMap.bitmapOf(2), Lists.newArrayList(), conf);
    validateResult(readClient, expectedData, blockIdBitmap3);

    readClient = new ShuffleReadClientImpl("LOCALFILE_AND_HDFS",
        appId, 0, 4, 100, 1, 10, 1000, HDFS_URI + "rss/multi_storage",
        blockIdBitmap4, Roaring64NavigableMap.bitmapOf(3), Lists.newArrayList(), conf);
    validateResult(readClient, expectedData, blockIdBitmap4);
  }

}