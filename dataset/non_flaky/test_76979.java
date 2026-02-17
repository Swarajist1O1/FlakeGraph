class DummyClass_76979 {
  @Test
  public void readTest7() {
    String testAppId = "localReadTest7";
    registerApp(testAppId, Lists.newArrayList(new PartitionRange(0, 0)));

    Map<Long, byte[]> expectedData = Maps.newHashMap();
    Roaring64NavigableMap blockIdBitmap = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap taskIdBitmap = Roaring64NavigableMap.bitmapOf(0, 1);

    List<ShuffleBlockInfo> blocks = createShuffleBlockList(
        0, 0, 0, 5, 30, blockIdBitmap, expectedData, mockSSI);
    sendTestData(testAppId, blocks);

    blocks = createShuffleBlockList(
        0, 0, 1, 5, 30, blockIdBitmap, expectedData, mockSSI);
    sendTestData(testAppId, blocks);

    blocks = createShuffleBlockList(
        0, 0, 2, 5, 30, blockIdBitmap, Maps.newHashMap(), mockSSI);
    sendTestData(testAppId, blocks);

    // unexpected taskAttemptId should be filtered
    ShuffleReadClientImpl readClient = new ShuffleReadClientImpl(StorageType.LOCALFILE.name(), testAppId, 0, 0, 100, 1,
        10, 1000, "", blockIdBitmap, taskIdBitmap, shuffleServerInfo, null);

    validateResult(readClient, expectedData);
    readClient.checkProcessedBlockIds();
    readClient.close();
  }

}