class DummyClass_76941 {
  @Test
  public void readTest1() throws Exception {
    String basePath = HDFS_URI + "clientReadTest1";
    HdfsShuffleWriteHandler writeHandler =
        new HdfsShuffleWriteHandler("appId", 0, 1, 1, basePath, "test1", conf);

    Map<Long, byte[]> expectedData = Maps.newHashMap();
    Roaring64NavigableMap blockIdBitmap = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap taskIdBitmap = Roaring64NavigableMap.bitmapOf(0);
    writeTestData(writeHandler, 2, 30, 0, expectedData,
        blockIdBitmap);

    ShuffleReadClientImpl readClient = new ShuffleReadClientImpl(StorageType.HDFS.name(), "appId", 0, 1, 100, 1,
        10, 1000, basePath, blockIdBitmap, taskIdBitmap, Lists.newArrayList(), new Configuration());

    TestUtils.validateResult(readClient, expectedData);
    readClient.checkProcessedBlockIds();
    readClient.close();

    blockIdBitmap.addLong(Constants.MAX_TASK_ATTEMPT_ID - 1);
    taskIdBitmap.addLong(Constants.MAX_TASK_ATTEMPT_ID - 1);
    readClient = new ShuffleReadClientImpl(StorageType.HDFS.name(), "appId", 0, 1, 100, 1,
        10, 1000, basePath, blockIdBitmap, taskIdBitmap, Lists.newArrayList(), new Configuration());
    TestUtils.validateResult(readClient, expectedData);
    try {
      // can't find all expected block id, data loss
      readClient.checkProcessedBlockIds();
      fail(EXPECTED_EXCEPTION_MESSAGE);
    } catch (Exception e) {
      assertTrue(e.getMessage().contains("Blocks read inconsistent:"));
    } finally {
      readClient.close();
    }
  }

}