class DummyClass_76946 {
  @Test
  public void readTest7() throws Exception {
    String basePath = HDFS_URI + "clientReadTest7";
    HdfsShuffleWriteHandler writeHandler =
        new HdfsShuffleWriteHandler("appId", 0, 0, 1, basePath, "test", conf);

    Map<Long, byte[]> expectedData1 = Maps.newHashMap();
    Map<Long, byte[]> expectedData2 = Maps.newHashMap();
    Roaring64NavigableMap blockIdBitmap1 = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap taskIdBitmap = Roaring64NavigableMap.bitmapOf(0);
    writeTestData(writeHandler, 10, 30, 0, expectedData1, blockIdBitmap1);

    Roaring64NavigableMap blockIdBitmap2 = Roaring64NavigableMap.bitmapOf();
    writeTestData(writeHandler, 10, 30, 0, expectedData2, blockIdBitmap2);

    writeTestData(writeHandler, 10, 30, 0, expectedData1, blockIdBitmap1);

    ShuffleReadClientImpl readClient1 = new ShuffleReadClientImpl(StorageType.HDFS.name(),
        "appId", 0, 0, 100, 2, 10, 100,
        basePath, blockIdBitmap1, taskIdBitmap, Lists.newArrayList(), new Configuration());
    ShuffleReadClientImpl readClient2 = new ShuffleReadClientImpl(StorageType.HDFS.name(),
        "appId", 0, 1, 100, 2, 10, 100,
        basePath, blockIdBitmap2, taskIdBitmap, Lists.newArrayList(), new Configuration());
    TestUtils.validateResult(readClient1, expectedData1);
    readClient1.checkProcessedBlockIds();
    readClient1.close();

    TestUtils.validateResult(readClient2, expectedData2);
    readClient2.checkProcessedBlockIds();
    readClient2.close();
  }

}