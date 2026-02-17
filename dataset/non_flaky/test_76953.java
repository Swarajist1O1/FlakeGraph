class DummyClass_76953 {
  @Test
  public void readTest14() throws Exception {
    String basePath = HDFS_URI + "clientReadTest14";
    HdfsShuffleWriteHandler writeHandler =
        new HdfsShuffleWriteHandler("appId", 0, 1, 1, basePath, "test1", conf);

    Map<Long, byte[]> expectedData = Maps.newHashMap();
    Roaring64NavigableMap blockIdBitmap = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap taskIdBitmap = Roaring64NavigableMap.bitmapOf(0, 2);
    writeDuplicatedData(writeHandler, 5, 30, 0, expectedData, blockIdBitmap);
    writeTestData(writeHandler, 5, 30, 1, Maps.newHashMap(), Roaring64NavigableMap.bitmapOf());
    writeTestData(writeHandler, 5, 30, 2, expectedData, blockIdBitmap);

    // unexpected taskAttemptId should be filtered
    ShuffleReadClientImpl readClient = new ShuffleReadClientImpl(StorageType.HDFS.name(), "appId", 0, 1, 100, 1,
        10, 1000, basePath, blockIdBitmap, taskIdBitmap, Lists.newArrayList(), new Configuration());

    TestUtils.validateResult(readClient, expectedData);
    assertEquals(15, readClient.getProcessedBlockIds().getLongCardinality());
    readClient.checkProcessedBlockIds();
    readClient.close();
  }

}