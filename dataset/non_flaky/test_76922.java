class DummyClass_76922 {
  @Test
  public void readTest1() throws Exception {
    String basePath = HDFS_URI + "readTest1";
    HdfsShuffleWriteHandler writeHandler =
        new HdfsShuffleWriteHandler("appId", 0, 0, 1, basePath, "test1", conf);

    Map<String, String> expectedData = Maps.newHashMap();
    Roaring64NavigableMap blockIdBitmap = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap taskIdBitmap = Roaring64NavigableMap.bitmapOf(0);
    writeTestData(writeHandler, 2, 5, expectedData,
        blockIdBitmap, "key", KRYO_SERIALIZER, 0);

    RssShuffleDataIterator rssShuffleDataIterator = getDataIterator(basePath, blockIdBitmap, taskIdBitmap);

    validateResult(rssShuffleDataIterator, expectedData, 10);

    blockIdBitmap.add(ClientUtils.getBlockId(0, 0, Constants.MAX_SEQUENCE_NO));
    rssShuffleDataIterator = getDataIterator(basePath, blockIdBitmap, taskIdBitmap);
    int recNum = 0;
    try {
      // can't find all expected block id, data loss
      while (rssShuffleDataIterator.hasNext()) {
        rssShuffleDataIterator.next();
        recNum++;
      }
      fail(EXPECTED_EXCEPTION_MESSAGE);
    } catch (Exception e) {
      assertTrue(e.getMessage().startsWith("Blocks read inconsistent:"));
    }
    assertEquals(10, recNum);
  }

}