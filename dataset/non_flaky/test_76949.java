class DummyClass_76949 {
  @Test
  public void readTest10() throws Exception {
    String basePath = HDFS_URI + "clientReadTest10";
    HdfsShuffleWriteHandler writeHandler =
        new HdfsShuffleWriteHandler("appId", 0, 0, 1, basePath, "test", conf);

    Map<Long, byte[]> expectedData = Maps.newHashMap();
    Roaring64NavigableMap blockIdBitmap = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap taskIdBitmap = Roaring64NavigableMap.bitmapOf(0);
    writeTestData(writeHandler, 5, 30, 0, expectedData, blockIdBitmap);
    Roaring64NavigableMap wrongBlockIdBitmap = Roaring64NavigableMap.bitmapOf();
    LongIterator iter = blockIdBitmap.getLongIterator();
    while (iter.hasNext()) {
      wrongBlockIdBitmap.addLong(iter.next() + (1 << Constants.TASK_ATTEMPT_ID_MAX_LENGTH));
    }

    ShuffleReadClientImpl readClient = new ShuffleReadClientImpl(StorageType.HDFS.name(),
        "appId", 0, 0, 100, 2, 10, 100,
        basePath, wrongBlockIdBitmap, taskIdBitmap, Lists.newArrayList(), new Configuration());
    assertNull(readClient.readShuffleBlockData());
    try {
      readClient.checkProcessedBlockIds();
      fail(EXPECTED_EXCEPTION_MESSAGE);
    } catch (Exception e) {
      assertTrue(e.getMessage().contains("Blocks read inconsistent:"));
    }
  }

}