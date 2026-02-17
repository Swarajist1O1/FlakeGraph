class DummyClass_77005 {
  @Test
  public void registerTest() {
    shuffleServerClient.registerShuffle(new RssRegisterShuffleRequest("registerTest", 0,
        Lists.newArrayList(new PartitionRange(0, 1))));
    RssGetShuffleResultRequest req = new RssGetShuffleResultRequest("registerTest", 0, 0);
    // no exception with getShuffleResult means register successfully
    shuffleServerClient.getShuffleResult(req);
    req = new RssGetShuffleResultRequest("registerTest", 0, 1);
    shuffleServerClient.getShuffleResult(req);
    shuffleServerClient.registerShuffle(new RssRegisterShuffleRequest("registerTest", 1,
        Lists.newArrayList(new PartitionRange(0, 0), new PartitionRange(1, 1), new PartitionRange(2, 2))));
    req = new RssGetShuffleResultRequest("registerTest", 1, 0);
    shuffleServerClient.getShuffleResult(req);
    req = new RssGetShuffleResultRequest("registerTest", 1, 1);
    shuffleServerClient.getShuffleResult(req);
    req = new RssGetShuffleResultRequest("registerTest", 1, 2);
    shuffleServerClient.getShuffleResult(req);
  }

}