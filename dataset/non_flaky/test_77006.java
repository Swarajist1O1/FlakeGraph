class DummyClass_77006 {
  @Test
  public void sendDataWithoutRegisterTest() throws Exception {
    List<ShuffleBlockInfo> blockInfos = Lists.newArrayList(new ShuffleBlockInfo(0, 0, 0, 100, 0,
        new byte[]{}, Lists.newArrayList(), 0, 100, 0));
    Map<Integer, List<ShuffleBlockInfo>> partitionToBlocks = Maps.newHashMap();
    partitionToBlocks.put(0, blockInfos);
    Map<Integer, Map<Integer, List<ShuffleBlockInfo>>> shuffleToBlocks = Maps.newHashMap();
    shuffleToBlocks.put(0, partitionToBlocks);

    RssSendShuffleDataRequest rssdr = new RssSendShuffleDataRequest(
        "sendDataWithoutRegisterTest", 3, 1000, shuffleToBlocks);
    shuffleServerClient.sendShuffleData(rssdr);
    assertEquals(132, shuffleServers.get(0).getPreAllocatedMemory());
    Thread.sleep(10000);
    assertEquals(0, shuffleServers.get(0).getPreAllocatedMemory());
  }

}