class DummyClass_76973 {
  @Test
  public void readTest1() {
    String testAppId = "localReadTest1";
    registerApp(testAppId, Lists.newArrayList(new PartitionRange(0, 0)));
    Map<Long, byte[]> expectedData = Maps.newHashMap();
    Roaring64NavigableMap blockIdBitmap = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap taskIdBitmap = Roaring64NavigableMap.bitmapOf(0);
    createTestData(testAppId, expectedData, blockIdBitmap, taskIdBitmap);
    blockIdBitmap.addLong((1 << Constants.TASK_ATTEMPT_ID_MAX_LENGTH));
    ShuffleReadClientImpl readClient;
    readClient = new ShuffleReadClientImpl(StorageType.LOCALFILE.name(), testAppId, 0, 0, 100, 1,
        10, 1000, "", blockIdBitmap, taskIdBitmap, shuffleServerInfo, null);
    validateResult(readClient, expectedData);
    try {
      // can't find all expected block id, data loss
      readClient.checkProcessedBlockIds();
      fail(EXPECTED_EXCEPTION_MESSAGE);
    } catch (Exception e) {
      assertTrue(e.getMessage().contains("Blocks read inconsistent:"));
    } finally {
      readClient.close();
    }
  }

}