class DummyClass_77004 {
  @Test
  public void shuffleResultTest() throws Exception {
    Map<Integer, List<Long>> partitionToBlockIds = Maps.newHashMap();
    List<Long> blockIds1 = getBlockIdList(1, 3);
    List<Long> blockIds2 = getBlockIdList(2, 2);
    List<Long> blockIds3 = getBlockIdList(3, 1);
    partitionToBlockIds.put(1, blockIds1);
    partitionToBlockIds.put(2, blockIds2);
    partitionToBlockIds.put(3, blockIds3);

    RssReportShuffleResultRequest request =
        new RssReportShuffleResultRequest("shuffleResultTest", 0, 0L, partitionToBlockIds, 1);
    try {
      shuffleServerClient.reportShuffleResult(request);
      fail("Exception should be thrown");
    } catch (Exception e) {
      assertTrue(e.getMessage().contains("error happened when report shuffle result"));
    }

    RssGetShuffleResultRequest req = new RssGetShuffleResultRequest("shuffleResultTest", 1, 1);
    try {
      shuffleServerClient.getShuffleResult(req);
      fail("Exception should be thrown");
    } catch (Exception e) {
      assertTrue(e.getMessage().contains("Can't get shuffle result"));
    }

    RssRegisterShuffleRequest rrsr = new RssRegisterShuffleRequest("shuffleResultTest", 100,
        Lists.newArrayList(new PartitionRange(0, 1)));
    shuffleServerClient.registerShuffle(rrsr);

    req = new RssGetShuffleResultRequest("shuffleResultTest", 0, 1);
    RssGetShuffleResultResponse result = shuffleServerClient.getShuffleResult(req);
    Roaring64NavigableMap blockIdBitmap = result.getBlockIdBitmap();
    assertEquals(Roaring64NavigableMap.bitmapOf(), blockIdBitmap);

    request =
        new RssReportShuffleResultRequest("shuffleResultTest", 0, 0L, partitionToBlockIds, 1);
    RssReportShuffleResultResponse response = shuffleServerClient.reportShuffleResult(request);
    assertEquals(ResponseStatusCode.SUCCESS, response.getStatusCode());
    req = new RssGetShuffleResultRequest("shuffleResultTest", 0, 1);
    result = shuffleServerClient.getShuffleResult(req);
    blockIdBitmap = result.getBlockIdBitmap();
    Roaring64NavigableMap expectedP1 = Roaring64NavigableMap.bitmapOf();
    addExpectedBlockIds(expectedP1, blockIds1);
    assertEquals(expectedP1, blockIdBitmap);

    req = new RssGetShuffleResultRequest("shuffleResultTest", 0, 2);
    result = shuffleServerClient.getShuffleResult(req);
    blockIdBitmap = result.getBlockIdBitmap();
    Roaring64NavigableMap expectedP2 = Roaring64NavigableMap.bitmapOf();
    addExpectedBlockIds(expectedP2, blockIds2);
    assertEquals(expectedP2, blockIdBitmap);

    req = new RssGetShuffleResultRequest("shuffleResultTest", 0, 3);
    result = shuffleServerClient.getShuffleResult(req);
    blockIdBitmap = result.getBlockIdBitmap();
    Roaring64NavigableMap expectedP3 = Roaring64NavigableMap.bitmapOf();
    addExpectedBlockIds(expectedP3, blockIds3);
    assertEquals(expectedP3, blockIdBitmap);

    partitionToBlockIds = Maps.newHashMap();
    blockIds1 = getBlockIdList(1, 3);
    blockIds2 = getBlockIdList(2, 2);
    blockIds3 = getBlockIdList(3, 1);
    partitionToBlockIds.put(1, blockIds1);
    partitionToBlockIds.put(2, blockIds2);
    partitionToBlockIds.put(3, blockIds3);

    request =
        new RssReportShuffleResultRequest("shuffleResultTest", 0, 1L, partitionToBlockIds, 1);
    shuffleServerClient.reportShuffleResult(request);

    req = new RssGetShuffleResultRequest("shuffleResultTest", 0, 1);
    result = shuffleServerClient.getShuffleResult(req);
    blockIdBitmap = result.getBlockIdBitmap();
    addExpectedBlockIds(expectedP1, blockIds1);
    assertEquals(expectedP1, blockIdBitmap);

    req = new RssGetShuffleResultRequest("shuffleResultTest", 0, 2);
    result = shuffleServerClient.getShuffleResult(req);
    blockIdBitmap = result.getBlockIdBitmap();
    addExpectedBlockIds(expectedP2, blockIds2);
    assertEquals(expectedP2, blockIdBitmap);

    req = new RssGetShuffleResultRequest("shuffleResultTest", 0, 3);
    result = shuffleServerClient.getShuffleResult(req);
    blockIdBitmap = result.getBlockIdBitmap();
    addExpectedBlockIds(expectedP3, blockIds3);
    assertEquals(expectedP3, blockIdBitmap);

    request =
        new RssReportShuffleResultRequest("shuffleResultTest", 1, 1L, Maps.newHashMap(), 1);
    shuffleServerClient.reportShuffleResult(request);
    req = new RssGetShuffleResultRequest("shuffleResultTest", 1, 1);
    result = shuffleServerClient.getShuffleResult(req);
    blockIdBitmap = result.getBlockIdBitmap();
    assertEquals(Roaring64NavigableMap.bitmapOf(), blockIdBitmap);

    // test with bitmapNum > 1
    partitionToBlockIds = Maps.newHashMap();
    blockIds1 = getBlockIdList(1, 3);
    blockIds2 = getBlockIdList(2, 2);
    blockIds3 = getBlockIdList(3, 1);
    partitionToBlockIds.put(1, blockIds1);
    partitionToBlockIds.put(2, blockIds2);
    partitionToBlockIds.put(3, blockIds3);
    request =
        new RssReportShuffleResultRequest("shuffleResultTest", 2, 1L, partitionToBlockIds, 3);
    shuffleServerClient.reportShuffleResult(request);
    // validate bitmap in shuffleTaskManager
    Roaring64NavigableMap[] bitmaps = shuffleServers.get(0).getShuffleTaskManager()
        .getPartitionsToBlockIds().get("shuffleResultTest").get(2);
    assertEquals(3, bitmaps.length);

    req = new RssGetShuffleResultRequest("shuffleResultTest", 2, 1);
    result = shuffleServerClient.getShuffleResult(req);
    blockIdBitmap = result.getBlockIdBitmap();
    expectedP1 = Roaring64NavigableMap.bitmapOf();
    addExpectedBlockIds(expectedP1, blockIds1);
    assertEquals(expectedP1, blockIdBitmap);

    req = new RssGetShuffleResultRequest("shuffleResultTest", 2, 2);
    result = shuffleServerClient.getShuffleResult(req);
    blockIdBitmap = result.getBlockIdBitmap();
    expectedP2 = Roaring64NavigableMap.bitmapOf();
    addExpectedBlockIds(expectedP2, blockIds2);
    assertEquals(expectedP2, blockIdBitmap);

    req = new RssGetShuffleResultRequest("shuffleResultTest", 2, 3);
    result = shuffleServerClient.getShuffleResult(req);
    blockIdBitmap = result.getBlockIdBitmap();
    expectedP3 = Roaring64NavigableMap.bitmapOf();
    addExpectedBlockIds(expectedP3, blockIds3);
    assertEquals(expectedP3, blockIdBitmap);

    partitionToBlockIds = Maps.newHashMap();
    blockIds1 = getBlockIdList((int) Constants.MAX_PARTITION_ID, 3);
    blockIds2 = getBlockIdList(2, 2);
    blockIds3 = getBlockIdList(3, 1);
    partitionToBlockIds.put((int) Constants.MAX_PARTITION_ID, blockIds1);
    partitionToBlockIds.put(2, blockIds2);
    partitionToBlockIds.put(3, blockIds3);
    // bimapNum = 2
    request =
        new RssReportShuffleResultRequest("shuffleResultTest", 4, 1L, partitionToBlockIds, 2);
    shuffleServerClient.reportShuffleResult(request);

    req = new RssGetShuffleResultRequest("shuffleResultTest", 4, (int) Constants.MAX_PARTITION_ID);
    result = shuffleServerClient.getShuffleResult(req);
    blockIdBitmap = result.getBlockIdBitmap();
    expectedP1 = Roaring64NavigableMap.bitmapOf();
    addExpectedBlockIds(expectedP1, blockIds1);
    assertEquals(expectedP1, blockIdBitmap);

    req = new RssGetShuffleResultRequest("shuffleResultTest", 4, 2);
    result = shuffleServerClient.getShuffleResult(req);
    blockIdBitmap = result.getBlockIdBitmap();
    expectedP2 = Roaring64NavigableMap.bitmapOf();
    addExpectedBlockIds(expectedP2, blockIds2);
    assertEquals(expectedP2, blockIdBitmap);

    req = new RssGetShuffleResultRequest("shuffleResultTest", 4, 3);
    result = shuffleServerClient.getShuffleResult(req);
    blockIdBitmap = result.getBlockIdBitmap();
    expectedP3 = Roaring64NavigableMap.bitmapOf();
    addExpectedBlockIds(expectedP3, blockIds3);
    assertEquals(expectedP3, blockIdBitmap);

    // wait resources are deleted
    Thread.sleep(12000);
    req = new RssGetShuffleResultRequest("shuffleResultTest", 1, 1);
    try {
      shuffleServerClient.getShuffleResult(req);
      fail("Exception should be thrown");
    } catch (Exception e) {
      assertTrue(e.getMessage().contains("Can't get shuffle result"));
    }
  }

}