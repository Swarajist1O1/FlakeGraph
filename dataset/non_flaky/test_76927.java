class DummyClass_76927 {
  @Test
  public void readTest7() throws Exception {
    String basePath = HDFS_URI + "readTest7";
    HdfsShuffleWriteHandler writeHandler =
        new HdfsShuffleWriteHandler("appId", 0, 0, 1, basePath, "test", conf);

    Map<String, String> expectedData = Maps.newHashMap();
    Roaring64NavigableMap blockIdBitmap = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap taskIdBitmap = Roaring64NavigableMap.bitmapOf(0);
    writeTestData(writeHandler, 2, 5, expectedData,
        blockIdBitmap, "key", KRYO_SERIALIZER, 0);

    RssShuffleDataIterator rssShuffleDataIterator = getDataIterator(basePath, blockIdBitmap, taskIdBitmap);

    // crc32 is incorrect
    try (MockedStatic<ChecksumUtils> checksumUtilsMock = Mockito.mockStatic(ChecksumUtils.class)) {
      checksumUtilsMock.when(() -> ChecksumUtils.getCrc32((ByteBuffer) any())).thenReturn(-1L);
      try {
        while (rssShuffleDataIterator.hasNext()) {
          rssShuffleDataIterator.next();
        }
        fail(EXPECTED_EXCEPTION_MESSAGE);
      } catch (Exception e) {
        assertTrue(e.getMessage().startsWith("Unexpected crc value"));
      }
    }
  }

}