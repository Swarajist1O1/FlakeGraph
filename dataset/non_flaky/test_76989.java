class DummyClass_76989 {
  @Test
  public void reportMultipleServerTest() throws Exception {
    String testAppId = "reportMultipleServerTest";

    shuffleWriteClientImpl.registerShuffle(shuffleServerInfo1,
        testAppId, 1, Lists.newArrayList(new PartitionRange(1, 1)));

    shuffleWriteClientImpl.registerShuffle(shuffleServerInfo2,
        testAppId, 1, Lists.newArrayList(new PartitionRange(2, 2)));

    Map<Integer, List<ShuffleServerInfo>> partitionToServers = Maps.newHashMap();
    partitionToServers.putIfAbsent(1, Lists.newArrayList(shuffleServerInfo1));
    partitionToServers.putIfAbsent(2, Lists.newArrayList(shuffleServerInfo2));
    Map<Integer, List<Long>> partitionToBlocks = Maps.newHashMap();
    List<Long> blockIds = Lists.newArrayList();
    for (int i = 0; i < 5; i++ ) {
      blockIds.add(ClientUtils.getBlockId(1, 0, i));
    }
    partitionToBlocks.put(1, blockIds);
    blockIds = Lists.newArrayList();
    for (int i = 0; i < 7; i++ ) {
      blockIds.add(ClientUtils.getBlockId(2, 0, i));
    }
    partitionToBlocks.put(2, blockIds);
    shuffleWriteClientImpl
        .reportShuffleResult(partitionToServers, testAppId, 1, 0, partitionToBlocks, 1);

    Roaring64NavigableMap bitmap = shuffleWriteClientImpl
        .getShuffleResult("GRPC", Sets.newHashSet(shuffleServerInfo1), testAppId,
        1, 0);
    assertTrue(bitmap.isEmpty());

    bitmap = shuffleWriteClientImpl
        .getShuffleResult("GRPC", Sets.newHashSet(shuffleServerInfo1), testAppId,
        1, 1);
    assertEquals(5, bitmap.getLongCardinality());
    for (int i = 0; i < 5; i++) {
      assertTrue(bitmap.contains(partitionToBlocks.get(1).get(i)));
    }

    bitmap = shuffleWriteClientImpl
        .getShuffleResult("GRPC", Sets.newHashSet(shuffleServerInfo1), testAppId,
        1, 2);
    assertTrue(bitmap.isEmpty());

    bitmap = shuffleWriteClientImpl
        .getShuffleResult("GRPC", Sets.newHashSet(shuffleServerInfo2), testAppId,
        1, 0);
    assertTrue(bitmap.isEmpty());

    bitmap = shuffleWriteClientImpl
        .getShuffleResult("GRPC", Sets.newHashSet(shuffleServerInfo2), testAppId,
        1, 1);
    assertTrue(bitmap.isEmpty());

    bitmap = shuffleWriteClientImpl
        .getShuffleResult("GRPC", Sets.newHashSet(shuffleServerInfo2), testAppId,
        1, 2);
    assertEquals(7, bitmap.getLongCardinality());
    for (int i = 0; i < 7; i++) {
      assertTrue(bitmap.contains(partitionToBlocks.get(2).get(i)));
    }
  }

}