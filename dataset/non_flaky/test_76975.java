class DummyClass_76975 {
  @Test
  public void readTest3() throws Exception {
    String testAppId = "localReadTest3";
    registerApp(testAppId, Lists.newArrayList(new PartitionRange(0, 0)));

    Map<Long, byte[]> expectedData = Maps.newHashMap();
    Roaring64NavigableMap blockIdBitmap = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap taskIdBitmap = Roaring64NavigableMap.bitmapOf(0);
    List<ShuffleBlockInfo> blocks = createShuffleBlockList(
        0, 0, 0, 2, 30, blockIdBitmap, expectedData, mockSSI);
    sendTestData(testAppId, blocks);

    ShuffleReadClientImpl readClient = new ShuffleReadClientImpl(StorageType.LOCALFILE.name(),
        testAppId, 0, 0, 100, 1, 10, 1000,
        "", blockIdBitmap, taskIdBitmap, shuffleServerInfo, null);
    FileUtils.deleteDirectory(new File(DATA_DIR1.getAbsolutePath() + "/" + testAppId + "/0/0-0"));
    FileUtils.deleteDirectory(new File(DATA_DIR2.getAbsolutePath() + "/" + testAppId + "/0/0-0"));
    // sleep to wait delete operation
    Thread.sleep(2000);

    try {
      readClient.readShuffleBlockData();
      fail(EXPECTED_EXCEPTION_MESSAGE);
    } catch (Exception e) {
      assertTrue(e.getMessage().contains("Failed to read shuffle index"));
    }
    readClient.close();
  }

}