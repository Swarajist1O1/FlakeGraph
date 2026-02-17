class DummyClass_77001 {
  @Test
  public void diskFaultTolerance() {
    String appId = "app_disk_fault_tolerance_data";
    Map<Long, byte[]> expectedData = Maps.newHashMap();

    Map<Integer, List<Integer>> map = Maps.newHashMap();
    map.put(2, Lists.newArrayList(1, 3));
    map.put(3, Lists.newArrayList(1));
    registerShuffle(appId, map);

    Roaring64NavigableMap blockIdBitmap1 = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap blockIdBitmap2 = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap blockIdBitmap3 = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap blockIdBitmap4 = Roaring64NavigableMap.bitmapOf();

    List<ShuffleBlockInfo> blocks1 = createShuffleBlockList(
        2, 1, 1,11, 10 * 1024 * 1024, blockIdBitmap1, expectedData);

    List<ShuffleBlockInfo> blocks2 = createShuffleBlockList(
        3, 1, 2,9, 10 * 1024 * 1024, blockIdBitmap2, expectedData);

    List<ShuffleBlockInfo> blocks3 = createShuffleBlockList(
        2, 3, 2,9, 10 * 1024 * 1024, blockIdBitmap3, expectedData);

    List<ShuffleBlockInfo> blocks4 = createShuffleBlockList(
        2, 1, 1, 11, 10 * 1024 * 1024, blockIdBitmap4, expectedData);

    assertEquals(1, ShuffleStorageUtils.getStorageIndex(2, appId, 2, 1));
    assertEquals(1, ShuffleStorageUtils.getStorageIndex(2, appId, 3, 1));
    assertEquals(1, ShuffleStorageUtils.getStorageIndex(2, appId, 2, 3));
    assertEquals(1, ShuffleStorageUtils.getStorageIndex(2, appId, 2, 1));
    try {
      sendSinglePartitionToShuffleServer(appId, 2, 1, 1, blocks1);
      sendSinglePartitionToShuffleServer(appId, 3, 1,2, blocks2);
      sendSinglePartitionToShuffleServer(appId, 2, 3, 2, blocks3);
      sendSinglePartitionToShuffleServer(appId, 2, 1, 1, blocks4);
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
    validateResult(appId, 2, 1, blockIdBitmap1, Roaring64NavigableMap.bitmapOf(1), expectedData);
    validateResult(appId, 3, 1, blockIdBitmap2, Roaring64NavigableMap.bitmapOf(2), expectedData);
    validateResult(appId, 2, 3, blockIdBitmap3, Roaring64NavigableMap.bitmapOf(2), expectedData);
  }

}