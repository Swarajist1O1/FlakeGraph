class DummyClass_76925 {
  @Test
  public void readTest4() throws Exception {
    String basePath = HDFS_URI + "readTest4";
    HdfsShuffleWriteHandler writeHandler =
        new HdfsShuffleWriteHandler("appId", 0, 0, 1, basePath, "test1", conf);

    Map<String, String> expectedData = Maps.newHashMap();
    Roaring64NavigableMap blockIdBitmap = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap taskIdBitmap = Roaring64NavigableMap.bitmapOf(0);
    writeTestData(writeHandler, 2, 5, expectedData,
        blockIdBitmap, "key", KRYO_SERIALIZER, 0);

    RssShuffleDataIterator rssShuffleDataIterator = getDataIterator(basePath, blockIdBitmap, taskIdBitmap);
    // data file is deleted after iterator initialization
    Path dataFile = new Path(basePath + "/appId/0/0-1/test1_0.data");
    fs.delete(dataFile, true);
    // sleep to wait delete operation
    Thread.sleep(10000);
    try {
      fs.listStatus(dataFile);
      fail("Index file should be deleted");
    } catch (Exception e) {
    }

    try {
      while (rssShuffleDataIterator.hasNext()) {
        rssShuffleDataIterator.next();
      }
      fail(EXPECTED_EXCEPTION_MESSAGE);
    } catch (Exception e) {
      assertTrue(e.getMessage().startsWith("Blocks read inconsistent: expected"));
    }
  }

}