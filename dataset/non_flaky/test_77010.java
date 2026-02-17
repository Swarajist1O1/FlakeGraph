class DummyClass_77010 {
  @Test
  public void readLocalDataTest() {
    String appId = "app_read_not_uploaded_data";
    Map<Long, byte[]> expectedData = Maps.newHashMap();
    RssRegisterShuffleRequest rr1 =  new RssRegisterShuffleRequest(appId, 1,
        Lists.newArrayList(new PartitionRange(0, 0)));
    RssRegisterShuffleRequest rr2 =  new RssRegisterShuffleRequest(appId, 1,
        Lists.newArrayList(new PartitionRange(1, 1)));
    RssRegisterShuffleRequest rr3 =  new RssRegisterShuffleRequest(appId, 1,
        Lists.newArrayList(new PartitionRange(2, 2)));
    RssRegisterShuffleRequest rr4 =  new RssRegisterShuffleRequest(appId, 1,
        Lists.newArrayList(new PartitionRange(3, 3)));
    shuffleServerClient.registerShuffle(rr1);
    shuffleServerClient.registerShuffle(rr2);
    shuffleServerClient.registerShuffle(rr3);
    shuffleServerClient.registerShuffle(rr4);

    Roaring64NavigableMap blockIdBitmap1 = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap blockIdBitmap2 = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap blockIdBitmap3 = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap blockIdBitmap4 = Roaring64NavigableMap.bitmapOf();

    List<ShuffleBlockInfo> blocks1 = createShuffleBlockList(
        1, 0, 1,3, 25, blockIdBitmap1, expectedData);
    List<ShuffleBlockInfo> blocks2 = createShuffleBlockList(
        1, 1, 2,5,1024 * 1024, blockIdBitmap2, expectedData);
    List<ShuffleBlockInfo> blocks3 = createShuffleBlockList(
        1, 2, 3,4, 25, blockIdBitmap3, expectedData);
    List<ShuffleBlockInfo> blocks4 = createShuffleBlockList(
        1, 3, 4,1, 1024 * 1024, blockIdBitmap4, expectedData);

    sendSinglePartitionToShuffleServer(appId, 1,0, 1L, blocks1);
    sendSinglePartitionToShuffleServer(appId, 1,1, 2L, blocks2);
    sendSinglePartitionToShuffleServer(appId, 1,2, 3L, blocks3);
    sendSinglePartitionToShuffleServer(appId, 1,3, 4L, blocks4);

    RssGetShuffleResultRequest rg1 = new RssGetShuffleResultRequest(appId, 1, 0);
    shuffleServerClient.getShuffleResult(rg1);
    RssGetShuffleResultRequest rg2 = new RssGetShuffleResultRequest(appId, 1, 1);
    shuffleServerClient.getShuffleResult(rg2);
    RssGetShuffleResultRequest rg3 = new RssGetShuffleResultRequest(appId, 1, 2);
    shuffleServerClient.getShuffleResult(rg3);
    RssGetShuffleResultRequest rg4 = new RssGetShuffleResultRequest(appId, 1, 3);
    shuffleServerClient.getShuffleResult(rg4);

    Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
    validateResult(appId, 1, 0, expectedData, getExpectBlockIds(blocks1));
    Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
    validateResult(appId, 1, 1, expectedData, getExpectBlockIds(blocks2));
    Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
    validateResult(appId, 1, 2, expectedData, getExpectBlockIds(blocks3));
    Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
    validateResult(appId, 1, 3, expectedData, getExpectBlockIds(blocks4));
    Uninterruptibles.sleepUninterruptibly(20, TimeUnit.SECONDS);
    boolean isException = false;
    try {
      readShuffleData(shuffleServerClient, appId, 1, 0,
          1, 10, 1000,  0);
    } catch (RuntimeException re) {
      isException = true;
      assertTrue(re.getMessage().contains("Can't get shuffle index"));
    }
    assertTrue(isException);
  }

}