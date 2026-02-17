class DummyClass_76924 {
  @Test
  public void readTest3() throws Exception {
    String basePath = HDFS_URI + "readTest3";
    HdfsShuffleWriteHandler writeHandler1 =
        new HdfsShuffleWriteHandler("appId", 0, 0, 1, basePath, "test3_1", conf);
    HdfsShuffleWriteHandler writeHandler2 =
        new HdfsShuffleWriteHandler("appId", 0, 0, 1, basePath, "test3_2", conf);

    Map<String, String> expectedData = Maps.newHashMap();
    Roaring64NavigableMap blockIdBitmap = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap taskIdBitmap = Roaring64NavigableMap.bitmapOf(0);
    writeTestData(writeHandler1, 2, 5, expectedData,
        blockIdBitmap, "key1", KRYO_SERIALIZER, 0);
    writeTestData(writeHandler2, 2, 5, expectedData,
        blockIdBitmap, "key2", KRYO_SERIALIZER, 0);

    // duplicate file created, it should be used in product environment
    String shuffleFolder = basePath + "/appId/0/0-1";
    FileUtil.copy(fs, new Path(shuffleFolder + "/test3_1_0.data"), fs,
        new Path(shuffleFolder + "/test3_1_0.cp.data"), false, conf);
    FileUtil.copy(fs, new Path(shuffleFolder + "/test3_1_0.index"), fs,
        new Path(shuffleFolder + "/test3_1_0.cp.index"), false, conf);
    FileUtil.copy(fs, new Path(shuffleFolder + "/test3_2_0.data"), fs,
        new Path(shuffleFolder + "/test3_2_0.cp.data"), false, conf);
    FileUtil.copy(fs, new Path(shuffleFolder + "/test3_2_0.index"), fs,
        new Path(shuffleFolder + "/test3_2_0.cp.index"), false, conf);

    RssShuffleDataIterator rssShuffleDataIterator = getDataIterator(basePath, blockIdBitmap, taskIdBitmap);

    validateResult(rssShuffleDataIterator, expectedData, 20);
  }

}