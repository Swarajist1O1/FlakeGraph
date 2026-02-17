class DummyClass_76974 {
  @Test
  public void readTest2() {
    String testAppId = "localReadTest2";
    registerApp(testAppId, Lists.newArrayList(new PartitionRange(0, 0)));

    Map<Long, byte[]> expectedData = Maps.newHashMap();
    Roaring64NavigableMap blockIdBitmap = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap taskIdBitmap = Roaring64NavigableMap.bitmapOf(0);
    List<ShuffleBlockInfo> blocks = createShuffleBlockList(
        0, 0, 0, 2, 30, blockIdBitmap, expectedData, mockSSI);
    sendTestData(testAppId, blocks);
    blocks = createShuffleBlockList(
        0, 0, 0, 2, 30, blockIdBitmap, expectedData, mockSSI);
    sendTestData(testAppId, blocks);

    ShuffleReadClientImpl readClient = new ShuffleReadClientImpl(StorageType.LOCALFILE.name(),
        testAppId, 0, 0, 100, 1, 10, 1000,
        "", blockIdBitmap, taskIdBitmap, shuffleServerInfo, null);

    validateResult(readClient, expectedData);
    readClient.checkProcessedBlockIds();
    readClient.close();
  }

}