class DummyClass_76944 {
  @Test
  public void readTest4() throws Exception {
    String basePath = HDFS_URI + "clientReadTest4";
    HdfsShuffleWriteHandler writeHandler =
        new HdfsShuffleWriteHandler("appId", 0, 0, 1, basePath, "test1", conf);

    Map<Long, byte[]> expectedData = Maps.newHashMap();
    Roaring64NavigableMap blockIdBitmap = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap taskIdBitmap = Roaring64NavigableMap.bitmapOf(0);
    writeTestData(writeHandler, 2, 30, 0, expectedData, blockIdBitmap);

    ShuffleReadClientImpl readClient = new ShuffleReadClientImpl(StorageType.HDFS.name(),
        "appId", 0, 1, 100, 2, 10, 1000,
        basePath, blockIdBitmap, taskIdBitmap, Lists.newArrayList(), new Configuration());
    Path dataFile = new Path(basePath + "/appId/0/0-1/test1_0.data");
    // data file is deleted after readClient checkExpectedBlockIds
    fs.delete(new Path(basePath + "/appId/0/0-1/test1_0.data"), true);
    // sleep to wait delete operation
    Thread.sleep(10000);

    assertNull(readClient.readShuffleBlockData());
    try {
      fs.listStatus(dataFile);
      fail("Index file should be deleted");
    } catch (Exception e) {
    }

    try {
      readClient.checkProcessedBlockIds();
      fail(EXPECTED_EXCEPTION_MESSAGE);
    } catch (Exception e) {
      assertTrue(e.getMessage().startsWith("Blocks read inconsistent: expected"));
    }
    readClient.close();
  }

}