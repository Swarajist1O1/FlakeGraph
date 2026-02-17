class DummyClass_76978 {
  @Test
  public void readTest6() {
    String testAppId = "localReadTest6";
    registerApp(testAppId, Lists.newArrayList(new PartitionRange(0, 0)));

    Map<Long, byte[]> expectedData = Maps.newHashMap();
    Roaring64NavigableMap blockIdBitmap = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap taskIdBitmap = Roaring64NavigableMap.bitmapOf(0);
    List<ShuffleBlockInfo> blocks = createShuffleBlockList(
        0, 0, 0, 5, 30, blockIdBitmap, expectedData, mockSSI);
    sendTestData(testAppId, blocks);

    Roaring64NavigableMap wrongBlockIdBitmap = Roaring64NavigableMap.bitmapOf();
    LongIterator iter = blockIdBitmap.getLongIterator();
    while (iter.hasNext()) {
      wrongBlockIdBitmap.addLong(iter.next() + (1 << Constants.TASK_ATTEMPT_ID_MAX_LENGTH));
    }

    ShuffleReadClientImpl readClient = new ShuffleReadClientImpl(StorageType.LOCALFILE.name(),
        testAppId, 0, 0, 100, 1, 10, 100,
        "", wrongBlockIdBitmap, taskIdBitmap, shuffleServerInfo, null);
    assertNull(readClient.readShuffleBlockData());
    try {
      readClient.checkProcessedBlockIds();
      fail(EXPECTED_EXCEPTION_MESSAGE);
    } catch (Exception e) {
      assertTrue(e.getMessage().contains("Blocks read inconsistent:"));
    }
  }

}