class DummyClass_76923 {
  @Test
  public void readTest2() throws Exception {
    String basePath = HDFS_URI + "readTest2";
    HdfsShuffleWriteHandler writeHandler1 =
        new HdfsShuffleWriteHandler("appId", 0, 0, 1, basePath, "test2_1", conf);
    HdfsShuffleWriteHandler writeHandler2 =
        new HdfsShuffleWriteHandler("appId", 0, 0, 1, basePath, "test2_2", conf);

    Map<String, String> expectedData = Maps.newHashMap();
    Roaring64NavigableMap blockIdBitmap = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap taskIdBitmap = Roaring64NavigableMap.bitmapOf(0);
    writeTestData(writeHandler1, 2, 5, expectedData,
        blockIdBitmap, "key1", KRYO_SERIALIZER, 0);
    writeTestData(writeHandler2, 2, 5, expectedData,
        blockIdBitmap, "key2", KRYO_SERIALIZER, 0);

    RssShuffleDataIterator rssShuffleDataIterator = getDataIterator(basePath, blockIdBitmap, taskIdBitmap);

    validateResult(rssShuffleDataIterator, expectedData, 20);
    assertEquals(20, rssShuffleDataIterator.getShuffleReadMetrics().recordsRead());
    assertEquals(256, rssShuffleDataIterator.getShuffleReadMetrics().remoteBytesRead());
    assertTrue(rssShuffleDataIterator.getShuffleReadMetrics().fetchWaitTime() > 0);
  }

}