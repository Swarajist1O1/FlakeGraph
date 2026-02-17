class DummyClass_76942 {
  @Test
  public void readTest2() throws Exception {
    String basePath = HDFS_URI + "clientReadTest2";
    HdfsShuffleWriteHandler writeHandler1 =
        new HdfsShuffleWriteHandler("appId", 0, 0, 1, basePath, "test2_1", conf);
    HdfsShuffleWriteHandler writeHandler2 =
        new HdfsShuffleWriteHandler("appId", 0, 0, 1, basePath, "test2_2", conf);

    Map<Long, byte[]> expectedData = Maps.newHashMap();
    Roaring64NavigableMap blockIdBitmap = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap taskIdBitmap = Roaring64NavigableMap.bitmapOf(0);
    writeTestData(writeHandler1, 2, 30, 0, expectedData, blockIdBitmap);
    writeTestData(writeHandler2, 2, 30, 0, expectedData, blockIdBitmap);

    ShuffleReadClientImpl readClient = new ShuffleReadClientImpl(StorageType.HDFS.name(),
        "appId", 0, 1, 100, 2, 10, 1000,
        basePath, blockIdBitmap, taskIdBitmap, Lists.newArrayList(), new Configuration());

    TestUtils.validateResult(readClient, expectedData);
    readClient.checkProcessedBlockIds();
    readClient.close();
  }

}