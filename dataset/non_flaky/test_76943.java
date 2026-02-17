class DummyClass_76943 {
  @Test
  public void readTest3() throws Exception {
    String basePath = HDFS_URI + "clientReadTest3";
    HdfsShuffleWriteHandler writeHandler1 =
        new HdfsShuffleWriteHandler("appId", 0, 0, 1, basePath, "test3_1", conf);
    HdfsShuffleWriteHandler writeHandler2 =
        new HdfsShuffleWriteHandler("appId", 0, 0, 1, basePath, "test3_2", conf);

    Map<Long, byte[]> expectedData = Maps.newHashMap();
    Roaring64NavigableMap blockIdBitmap = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap taskIdBitmap = Roaring64NavigableMap.bitmapOf(0);
    writeTestData(writeHandler1, 2, 30, 0, expectedData, blockIdBitmap);
    writeTestData(writeHandler2, 2, 30, 0, expectedData, blockIdBitmap);

    // duplicate file created, it should be used in product environment
    String shuffleFolder = basePath + "/appId/0/0-1";
    FileUtil.copy(fs, new Path(shuffleFolder + "/test3_1_0.data"), fs,
        new Path(basePath + "/test3_1.cp.data"), false, conf);
    FileUtil.copy(fs, new Path(shuffleFolder + "/test3_1_0.index"), fs,
        new Path(basePath + "/test3_1.cp.index"), false, conf);
    FileUtil.copy(fs, new Path(shuffleFolder + "/test3_2_0.data"), fs,
        new Path(basePath + "/test3_2.cp.data"), false, conf);
    FileUtil.copy(fs, new Path(shuffleFolder + "/test3_2_0.index"), fs,
        new Path(basePath + "/test3_2.cp.index"), false, conf);

    ShuffleReadClientImpl readClient = new ShuffleReadClientImpl(StorageType.HDFS.name(),
        "appId", 0, 1, 100, 2, 10, 1000,
        basePath, blockIdBitmap, taskIdBitmap, Lists.newArrayList(), new Configuration());

    TestUtils.validateResult(readClient, expectedData);
    readClient.checkProcessedBlockIds();
    readClient.close();
  }

}