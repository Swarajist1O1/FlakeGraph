class DummyClass_76926 {
  @Test
  public void readTest5() throws Exception {
    String basePath = HDFS_URI + "readTest5";
    HdfsShuffleWriteHandler writeHandler =
        new HdfsShuffleWriteHandler("appId", 0, 0, 1, basePath, "test", conf);

    Map<String, String> expectedData = Maps.newHashMap();
    Roaring64NavigableMap blockIdBitmap = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap taskIdBitmap = Roaring64NavigableMap.bitmapOf(0);
    writeTestData(writeHandler, 2, 5, expectedData,
        blockIdBitmap, "key", KRYO_SERIALIZER, 0);

    RssShuffleDataIterator rssShuffleDataIterator = getDataIterator(basePath, blockIdBitmap, taskIdBitmap);
    // index file is deleted after iterator initialization, it should be ok, all index infos are read already
    Path indexFile = new Path(basePath + "/appId/0/0-1/test.index");
    fs.delete(indexFile, true);
    // sleep to wait delete operation
    Thread.sleep(10000);
    try {
      fs.listStatus(indexFile);
      fail("Index file should be deleted");
    } catch (Exception e) {
    }
    validateResult(rssShuffleDataIterator, expectedData, 10);
  }

}