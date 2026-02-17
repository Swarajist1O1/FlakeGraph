class DummyClass_76950 {
  @Test
  public void readTest11() throws Exception {
    String basePath = HDFS_URI + "clientReadTest11";
    HdfsShuffleWriteHandler writeHandler =
        new HdfsShuffleWriteHandler("appId", 0, 1, 1, basePath, "test1", conf);

    Map<Long, byte[]> expectedData = Maps.newHashMap();
    Roaring64NavigableMap blockIdBitmap = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap taskIdBitmap = Roaring64NavigableMap.bitmapOf(0);
    writeTestData(writeHandler, 10, 30, 0, expectedData, blockIdBitmap);

    // test with different indexReadLimit to validate result
    ShuffleReadClientImpl readClient = new ShuffleReadClientImpl(StorageType.HDFS.name(), "appId", 0, 1, 1, 1,
        10, 1000, basePath, blockIdBitmap, taskIdBitmap, Lists.newArrayList(), new Configuration());

    TestUtils.validateResult(readClient, expectedData);
    readClient.checkProcessedBlockIds();
    readClient.close();

    readClient = new ShuffleReadClientImpl(StorageType.HDFS.name(), "appId", 0, 1, 2, 1,
        10, 1000, basePath, blockIdBitmap, taskIdBitmap, Lists.newArrayList(), new Configuration());

    TestUtils.validateResult(readClient, expectedData);
    readClient.checkProcessedBlockIds();
    readClient.close();

    readClient = new ShuffleReadClientImpl(StorageType.HDFS.name(), "appId", 0, 1, 3, 1,
        10, 1000, basePath, blockIdBitmap, taskIdBitmap, Lists.newArrayList(), new Configuration());

    TestUtils.validateResult(readClient, expectedData);
    readClient.checkProcessedBlockIds();
    readClient.close();

    readClient = new ShuffleReadClientImpl(StorageType.HDFS.name(), "appId", 0, 1, 10, 1,
        10, 1000, basePath, blockIdBitmap, taskIdBitmap, Lists.newArrayList(), new Configuration());

    TestUtils.validateResult(readClient, expectedData);
    readClient.checkProcessedBlockIds();
    readClient.close();

    readClient = new ShuffleReadClientImpl(StorageType.HDFS.name(), "appId", 0, 1, 11, 1,
        10, 1000, basePath, blockIdBitmap, taskIdBitmap, Lists.newArrayList(), new Configuration());

    TestUtils.validateResult(readClient, expectedData);
    readClient.checkProcessedBlockIds();
    readClient.close();
  }

}