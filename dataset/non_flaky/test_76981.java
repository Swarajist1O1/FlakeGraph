class DummyClass_76981 {
  @Test
  public void readTest9() throws Exception {
    String testAppId = "localReadTest9";
    registerApp(testAppId, Lists.newArrayList(new PartitionRange(0, 0)));
    Map<Long, byte[]> expectedData = Maps.newHashMap();
    Roaring64NavigableMap blockIdBitmap = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap taskIdBitmap = Roaring64NavigableMap.bitmapOf(0);

    List<ShuffleBlockInfo> blocks;
    ShuffleReadClientImpl readClient;

    createTestData(testAppId, expectedData, blockIdBitmap, taskIdBitmap);
    Roaring64NavigableMap beforeAdded = RssUtils.deserializeBitMap(RssUtils.serializeBitMap(blockIdBitmap));
    // write data by another task, read data again, the cache for index file should be updated
    blocks = createShuffleBlockList(
        0, 0, 1, 3, 25, blockIdBitmap, Maps.newHashMap(), mockSSI);
    sendTestData(testAppId, blocks);
    // test with un-changed expected blockId
    readClient = new ShuffleReadClientImpl(StorageType.LOCALFILE.name(), testAppId, 0, 0, 100, 1,
        10, 1000, "", beforeAdded, taskIdBitmap,
        shuffleServerInfo, null);
    validateResult(readClient, expectedData);
    readClient.checkProcessedBlockIds();
    readClient.close();

    // test with changed expected blockId
    readClient = new ShuffleReadClientImpl(StorageType.LOCALFILE.name(), testAppId, 0, 0, 100, 1,
        10, 1000, "", blockIdBitmap, taskIdBitmap,
        shuffleServerInfo, null);
    validateResult(readClient, expectedData);
    readClient.checkProcessedBlockIds();
    readClient.close();
  }

}