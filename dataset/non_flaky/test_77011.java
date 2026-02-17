class DummyClass_77011 {
  @Test
  public void readMixedDataTest() {
    String appId = "app_read_mix_data";
    RssRegisterShuffleRequest rr1 =  new RssRegisterShuffleRequest(appId, 0,
        Lists.newArrayList(new PartitionRange(0, 0)));
    shuffleServerClient.registerShuffle(rr1);

    Map<Long, byte[]> expectedData = Maps.newHashMap();
    Set<Long> expectedBlock1 = Sets.newHashSet();

    Roaring64NavigableMap blockIdBitmap1 = Roaring64NavigableMap.bitmapOf();

    List<ShuffleBlockInfo> blocks1 = createShuffleBlockList(
        0, 0, 1,15, 1024 * 1024, blockIdBitmap1, expectedData);

    blocks1.forEach(b -> expectedBlock1.add(b.getBlockId()));

    Map<Integer, List<ShuffleBlockInfo>> partitionToBlocks = Maps.newHashMap();
    partitionToBlocks.put(0, blocks1);
    Map<Integer, Map<Integer, List<ShuffleBlockInfo>>> shuffleToBlocks = Maps.newHashMap();
    shuffleToBlocks.put(0, partitionToBlocks);
    RssSendShuffleDataRequest rs1 = new RssSendShuffleDataRequest(appId, 3, 1000, shuffleToBlocks);
    shuffleServerClient.sendShuffleData(rs1);

    RssSendCommitRequest rc1 = new RssSendCommitRequest(appId, 0);
    shuffleServerClient.sendCommit(rc1);
    RssFinishShuffleRequest rf1 = new RssFinishShuffleRequest(appId, 0);
    shuffleServerClient.finishShuffle(rf1);
    Map<Integer, List<Long>> partitionToBlockIds = Maps.newHashMap();
    partitionToBlockIds.put(0, new ArrayList<>(expectedBlock1));
    RssReportShuffleResultRequest rrp1 = new RssReportShuffleResultRequest(
        appId, 0, 1L, partitionToBlockIds, 1);
    shuffleServerClient.reportShuffleResult(rrp1);

    RssGetShuffleResultRequest rg1 = new RssGetShuffleResultRequest(appId, 0, 0);
    shuffleServerClient.getShuffleResult(rg1);

    ShuffleReadClientImpl readClient = new ShuffleReadClientImpl("LOCALFILE_AND_HDFS",
        appId, 0, 0, 100, 1, 10, 1000, HDFS_URI + "rss/multi_storage",
        blockIdBitmap1, Roaring64NavigableMap.bitmapOf(1), Lists.newArrayList(new ShuffleServerInfo("test", LOCALHOST, SHUFFLE_SERVER_PORT)), conf);

    CompressedShuffleBlock csb = readClient.readShuffleBlockData();
    Roaring64NavigableMap matched = Roaring64NavigableMap.bitmapOf();
    assertNotNull(csb);
    assertNotNull(csb.getByteBuffer());
    for (Map.Entry<Long, byte[]> entry : expectedData.entrySet()) {
      if (compareByte(entry.getValue(), csb.getByteBuffer())) {
        matched.addLong(entry.getKey());
      }
    }
    wait(appId);

    csb = readClient.readShuffleBlockData();
    while (csb != null && csb.getByteBuffer() != null) {
      for (Map.Entry<Long, byte[]> entry : expectedData.entrySet()) {
        if (compareByte(entry.getValue(), csb.getByteBuffer())) {
          matched.addLong(entry.getKey());
          break;
        }
      }
      csb = readClient.readShuffleBlockData();
    }
    assertTrue(blockIdBitmap1.equals(matched));

    boolean isException = false;
    try {
      readShuffleData(shuffleServerClient, appId, 0, 0,
          1, 10, 1000, 0);
    } catch (RuntimeException re) {
      isException = true;
      assertTrue(re.getMessage().contains("Can't get shuffle index"));
    }
    assertTrue(isException);

    List<ShuffleBlockInfo> blocks5 = createShuffleBlockList(
        0, 0, 1,15, 1024 * 1024, blockIdBitmap1, expectedData);
    partitionToBlocks.clear();
    shuffleToBlocks.clear();
    partitionToBlocks.put(0, blocks5);
    shuffleToBlocks.put(0, partitionToBlocks);
    RssSendShuffleDataRequest rs5 = new RssSendShuffleDataRequest(appId, 3, 1000, shuffleToBlocks);
    DiskItem diskItem = shuffleServers.get(0).getMultiStorageManager().getDiskItem(appId, 0, 0);
    String path = ShuffleStorageUtils.getFullShuffleDataFolder(diskItem.getBasePath(),
        ShuffleStorageUtils.getShuffleDataPath(appId, 0, 0, 0));
    File file = new File(path);
    assertFalse(file.exists());
    try {
      shuffleServerClient.sendShuffleData(rs5);
      shuffleServerClient.sendCommit(rc1);
      shuffleServerClient.finishShuffle(rf1);
      shuffleServerClient.reportShuffleResult(rrp1);
    } catch (Exception e) {
      fail();
    }
    assertFalse(file.exists());
  }

}