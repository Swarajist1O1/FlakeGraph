class DummyClass_76976 {
  @Test
  public void readTest4() {
    String testAppId = "localReadTest4";
    registerApp(testAppId, Lists.newArrayList(new PartitionRange(0, 1)));

    Map<Long, byte[]> expectedData1 = Maps.newHashMap();
    Map<Long, byte[]> expectedData2 = Maps.newHashMap();
    Roaring64NavigableMap blockIdBitmap1 = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap taskIdBitmap = Roaring64NavigableMap.bitmapOf(0);
    List<ShuffleBlockInfo> blocks = createShuffleBlockList(
        0, 0, 0, 10, 30, blockIdBitmap1, expectedData1, mockSSI);
    sendTestData(testAppId, blocks);

    Roaring64NavigableMap blockIdBitmap2 = Roaring64NavigableMap.bitmapOf();
    blocks = createShuffleBlockList(
        0, 1, 0, 10, 30, blockIdBitmap2, expectedData2, mockSSI);
    sendTestData(testAppId, blocks);

    blocks = createShuffleBlockList(
        0, 0, 0, 10, 30, blockIdBitmap1, expectedData1, mockSSI);
    sendTestData(testAppId, blocks);

    ShuffleReadClientImpl readClient1 = new ShuffleReadClientImpl(StorageType.LOCALFILE.name(),
        testAppId, 0, 0, 100, 2, 10, 100,
        "", blockIdBitmap1, taskIdBitmap, shuffleServerInfo, null);
    ShuffleReadClientImpl readClient2 = new ShuffleReadClientImpl(StorageType.LOCALFILE.name(),
        testAppId, 0, 1, 100, 2, 10, 100,
        "", blockIdBitmap2, taskIdBitmap, shuffleServerInfo, null);
    validateResult(readClient1, expectedData1);
    readClient1.checkProcessedBlockIds();
    readClient1.close();

    validateResult(readClient2, expectedData2);
    readClient2.checkProcessedBlockIds();
    readClient2.close();
  }

}