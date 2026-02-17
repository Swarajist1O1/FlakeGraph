class DummyClass_76990 {
  @Test
  public void writeReadTest() throws Exception {
    String testAppId = "writeReadTest";
    shuffleWriteClientImpl.registerShuffle(shuffleServerInfo1,
        testAppId, 0, Lists.newArrayList(new PartitionRange(0, 0)));
    shuffleWriteClientImpl.registerShuffle(shuffleServerInfo2,
        testAppId, 0, Lists.newArrayList(new PartitionRange(0, 0)));
    Map<Long, byte[]> expectedData = Maps.newHashMap();
    Roaring64NavigableMap blockIdBitmap = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap taskIdBitmap = Roaring64NavigableMap.bitmapOf(0);

    List<ShuffleBlockInfo> blocks = createShuffleBlockList(
        0, 0, 0, 3, 25, blockIdBitmap,
        expectedData, Lists.newArrayList(shuffleServerInfo1, shuffleServerInfo2));
    shuffleWriteClientImpl.sendShuffleData(testAppId, blocks);
    // send 1st commit, finish commit won't be sent to Shuffle server and data won't be persisted to disk
    boolean commitResult = shuffleWriteClientImpl
        .sendCommit(Sets.newHashSet(shuffleServerInfo1, shuffleServerInfo2), testAppId, 0, 2);
    assertTrue(commitResult);

    ShuffleReadClientImpl readClient = new ShuffleReadClientImpl(StorageType.LOCALFILE.name(), testAppId, 0, 0, 100, 1,
        10, 1000, "", blockIdBitmap, taskIdBitmap,
        Lists.newArrayList(shuffleServerInfo1, shuffleServerInfo2), null);

    try {
      readClient.readShuffleBlockData();
      fail(EXPECTED_EXCEPTION_MESSAGE);
    } catch (Exception e) {
      assertTrue(e.getMessage().contains("Failed to read shuffle index for"));
    }
    readClient.close();

    // send 2nd commit, data will be persisted to disk
    commitResult = shuffleWriteClientImpl
        .sendCommit(Sets.newHashSet(shuffleServerInfo1, shuffleServerInfo2), testAppId, 0, 2);
    assertTrue(commitResult);
    readClient = new ShuffleReadClientImpl(StorageType.LOCALFILE.name(), testAppId, 0, 0, 100, 1,
        10, 1000, "", blockIdBitmap, taskIdBitmap,
        Lists.newArrayList(shuffleServerInfo1, shuffleServerInfo2), null);
    validateResult(readClient, expectedData);
    readClient.checkProcessedBlockIds();
    readClient.close();

    // commit will be failed because of fakeIp
    commitResult = shuffleWriteClientImpl.sendCommit(Sets.newHashSet(new ShuffleServerInfo(
        "127.0.0.1-20001", "fakeIp", SHUFFLE_SERVER_PORT)), testAppId, 0, 2);
    assertFalse(commitResult);

    // wait resource to be deleted
    Thread.sleep(6000);

    // commit is ok, but finish shuffle rpc will failed because resource was deleted
    commitResult = shuffleWriteClientImpl
        .sendCommit(Sets.newHashSet(shuffleServerInfo1, shuffleServerInfo2), testAppId, 0, 2);
    assertFalse(commitResult);
  }

}