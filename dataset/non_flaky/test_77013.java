class DummyClass_77013 {
  @Test
  public void removeMetaTest() {
    String appId = "app_read_diskusage_data_without_report";
    Map<Long, byte[]> expectedData = Maps.newHashMap();
    RssRegisterShuffleRequest rr1 =  new RssRegisterShuffleRequest(appId, 2,
        Lists.newArrayList(new PartitionRange(0, 0)));
    shuffleServerClient.registerShuffle(rr1);
    RssRegisterShuffleRequest rr2 =  new RssRegisterShuffleRequest(appId, 3,
        Lists.newArrayList(new PartitionRange(1, 1)));
    shuffleServerClient.registerShuffle(rr2);

    Roaring64NavigableMap blockIdBitmap1 = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap blockIdBitmap2 = Roaring64NavigableMap.bitmapOf();

    List<ShuffleBlockInfo> blocks1 = createShuffleBlockList(
        2, 0, 1,30, 10 * 1024 * 1024, blockIdBitmap1, expectedData);
    List<ShuffleBlockInfo> blocks2 = createShuffleBlockList(
        3, 1, 2,9, 10 * 1024 * 1024, blockIdBitmap2, expectedData);

    sendSinglePartitionToShuffleServerWithoutReport(appId, 2, 2, 2, blocks1);
    sendSinglePartitionToShuffleServerWithoutReport(appId, 3, 1,2, blocks2);
    shuffleServers.get(0).getShuffleTaskManager().removeResources(appId);
    DiskItem item = shuffleServers.get(0).getMultiStorageManager().getDiskItem(appId, 2, 0);
    Uninterruptibles.sleepUninterruptibly(1500, TimeUnit.MILLISECONDS);
    Set<String> keys = item.getShuffleMetaSet();
    assertTrue(keys.isEmpty());
    item = shuffleServers.get(0).getMultiStorageManager().getDiskItem(appId, 3, 1);
    keys = item.getShuffleMetaSet();
    assertTrue(keys.isEmpty());

    appId = "app_read_diskusage_data_with_report";
    rr1 =  new RssRegisterShuffleRequest(appId, 0, Lists.newArrayList(new PartitionRange(0, 0)));
    shuffleServerClient.registerShuffle(rr1);
    blocks1 = createShuffleBlockList(
        0, 0, 1,30, 10 * 1024, blockIdBitmap1, expectedData);
    sendSinglePartitionToShuffleServer(appId, 0, 0, 2, blocks1);
    shuffleServers.get(0).getShuffleTaskManager().removeResources(appId);
    item = shuffleServers.get(0).getMultiStorageManager().getDiskItem(appId, 0, 0);
    Uninterruptibles.sleepUninterruptibly(1500, TimeUnit.MILLISECONDS);
    keys = item.getShuffleMetaSet();
    assertTrue(keys.isEmpty());
  }

}