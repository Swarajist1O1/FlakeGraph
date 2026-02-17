class DummyClass_76945 {
  @Test
  public void readTest5() throws Exception {
    String basePath = HDFS_URI + "clientReadTest5";
    HdfsShuffleWriteHandler writeHandler =
        new HdfsShuffleWriteHandler("appId", 0, 0, 1, basePath, "test", conf);

    Map<Long, byte[]> expectedData = Maps.newHashMap();
    Roaring64NavigableMap blockIdBitmap = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap taskIdBitmap = Roaring64NavigableMap.bitmapOf(0);
    writeTestData(writeHandler, 2, 30, 0, expectedData, blockIdBitmap);

    ShuffleReadClientImpl readClient = new ShuffleReadClientImpl(StorageType.HDFS.name(),
        "appId", 0, 1, 100, 2, 10, 1000,
        basePath, blockIdBitmap, taskIdBitmap, Lists.newArrayList(), new Configuration());
    // index file is deleted after iterator initialization, it should be ok, all index infos are read already
    Path indexFile = new Path(basePath + "/appId/0/0-1/test.index");
    fs.delete(indexFile, true);
    readClient.close();

    assertNull(readClient.readShuffleBlockData());
  }

}