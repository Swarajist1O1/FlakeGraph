class DummyClass_76951 {
  @Test
  public void readTest12() throws Exception {
    String basePath = HDFS_URI + "clientReadTest12";
    HdfsShuffleWriteHandler writeHandler =
        new HdfsShuffleWriteHandler("appId", 0, 1, 1, basePath, "test1", conf);

    Map<Long, byte[]> expectedData = Maps.newHashMap();
    Roaring64NavigableMap blockIdBitmap = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap taskIdBitmap = Roaring64NavigableMap.bitmapOf(0, 1);
    writeTestData(writeHandler, 5, 30, 0, expectedData, blockIdBitmap);
    writeTestData(writeHandler, 5, 30, 1, expectedData, blockIdBitmap);
    writeTestData(writeHandler, 5, 30, 2, Maps.newHashMap(), blockIdBitmap);

    // unexpected taskAttemptId should be filtered
    ShuffleReadClientImpl readClient = new ShuffleReadClientImpl(StorageType.HDFS.name(), "appId", 0, 1, 100, 1,
        10, 1000, basePath, blockIdBitmap, taskIdBitmap, Lists.newArrayList(), new Configuration());

    TestUtils.validateResult(readClient, expectedData);
    assertEquals(15, readClient.getProcessedBlockIds().getLongCardinality());
    readClient.checkProcessedBlockIds();
    readClient.close();
  }

}