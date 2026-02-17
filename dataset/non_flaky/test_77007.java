class DummyClass_77007 {
  @Test
  public void multipleShuffleResultTest() throws Exception {
    Set<Long> expectedBlockIds = Sets.newConcurrentHashSet();
    RssRegisterShuffleRequest rrsr = new RssRegisterShuffleRequest("multipleShuffleResultTest", 100,
        Lists.newArrayList(new PartitionRange(0, 1)));
    shuffleServerClient.registerShuffle(rrsr);

    Runnable r1 = () -> {
      for (int i = 0; i < 100; i++) {
        Map<Integer, List<Long>> ptbs = Maps.newHashMap();
        List<Long> blockIds = Lists.newArrayList();
        Long blockId = ClientUtils.getBlockId(1, 0, i);
        expectedBlockIds.add(blockId);
        blockIds.add(blockId);
        ptbs.put(1, blockIds);
        RssReportShuffleResultRequest req1 =
            new RssReportShuffleResultRequest("multipleShuffleResultTest", 1, 0, ptbs, 1);
        shuffleServerClient.reportShuffleResult(req1);
      }
    };
    Runnable r2 = () -> {
      for (int i = 100; i < 200; i++) {
        Map<Integer, List<Long>> ptbs = Maps.newHashMap();
        List<Long> blockIds = Lists.newArrayList();
        Long blockId = ClientUtils.getBlockId(1, 1, i);
        expectedBlockIds.add(blockId);
        blockIds.add(blockId);
        ptbs.put(1, blockIds);
        RssReportShuffleResultRequest req1 =
            new RssReportShuffleResultRequest("multipleShuffleResultTest", 1, 1, ptbs, 1);
        shuffleServerClient.reportShuffleResult(req1);
      }
    };
    Runnable r3 = () -> {
      for (int i = 200; i < 300; i++) {
        Map<Integer, List<Long>> ptbs = Maps.newHashMap();
        List<Long> blockIds = Lists.newArrayList();
        Long blockId = ClientUtils.getBlockId(1, 2, i);
        expectedBlockIds.add(blockId);
        blockIds.add(blockId);
        ptbs.put(1, blockIds);
        RssReportShuffleResultRequest req1 =
            new RssReportShuffleResultRequest("multipleShuffleResultTest", 1, 2, ptbs, 1);
        shuffleServerClient.reportShuffleResult(req1);
      }
    };
    Thread t1 = new Thread(r1);
    Thread t2 = new Thread(r2);
    Thread t3 = new Thread(r3);
    t1.start();
    t2.start();
    t3.start();
    t1.join();
    t2.join();
    t3.join();

    Roaring64NavigableMap blockIdBitmap = Roaring64NavigableMap.bitmapOf();
    for (Long blockId : expectedBlockIds) {
      blockIdBitmap.addLong(blockId);
    }

    RssGetShuffleResultRequest req = new RssGetShuffleResultRequest(
        "multipleShuffleResultTest", 1, 1);
    RssGetShuffleResultResponse result = shuffleServerClient.getShuffleResult(req);
    Roaring64NavigableMap actualBlockIdBitmap = result.getBlockIdBitmap();
    assertEquals(blockIdBitmap, actualBlockIdBitmap);
  }

}