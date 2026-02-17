class DummyClass_77000 {
  @Test
  public void hdfsFaultTolerance() {
    try {
      String appId = "app_hdfs_fault_tolerance_data";
      Map<Long, byte[]> expectedData = Maps.newHashMap();
      Map<Integer, List<Integer>> map = Maps.newHashMap();
      map.put(2, Lists.newArrayList(0, 3));
      map.put(3, Lists.newArrayList(3));
      registerShuffle(appId, map);

      Roaring64NavigableMap blockIdBitmap1 = Roaring64NavigableMap.bitmapOf();
      Roaring64NavigableMap blockIdBitmap2 = Roaring64NavigableMap.bitmapOf();
      Roaring64NavigableMap blockIdBitmap3 = Roaring64NavigableMap.bitmapOf();

      List<ShuffleBlockInfo> blocks1 = createShuffleBlockList(
          2, 0, 1,11, 10 * 1024 * 1024, blockIdBitmap1, expectedData);

      List<ShuffleBlockInfo> blocks2 = createShuffleBlockList(
          3, 3, 2,9, 10 * 1024 * 1024, blockIdBitmap2, expectedData);

      List<ShuffleBlockInfo> blocks3 = createShuffleBlockList(
          2, 3, 2,9, 10 * 1024 * 1024, blockIdBitmap3, expectedData);

      assertEquals(0, ShuffleStorageUtils.getStorageIndex(2, appId, 2, 0));
      assertEquals(0, ShuffleStorageUtils.getStorageIndex(2, appId, 3, 3));
      assertEquals(0, ShuffleStorageUtils.getStorageIndex(2, appId, 2, 3));
      assertEquals(1, cluster.getDataNodes().size());
      cluster.stopDataNode(0);
      assertEquals(0, cluster.getDataNodes().size());

      sendSinglePartitionToShuffleServer(appId, 2, 0, 1, blocks1);
      boolean isException = false;
      try {
        sendSinglePartitionToShuffleServer(appId, 3, 3,2, blocks2);
      } catch (RuntimeException re) {
        isException = true;
        assertTrue(re.getMessage().contains("Fail to finish"));
      }
      assertTrue(isException);

      cluster.startDataNodes(conf, 1, true, HdfsServerConstants.StartupOption.REGULAR,
          null, null, null, false, true);
      assertEquals(1, cluster.getDataNodes().size());

      sendSinglePartitionToShuffleServer(appId, 2, 3, 2, blocks3);

      validateResult(appId, 2, 0, blockIdBitmap1, Roaring64NavigableMap.bitmapOf(1), expectedData);
      validateResult(appId, 2, 3, blockIdBitmap3, Roaring64NavigableMap.bitmapOf(2), expectedData);
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

}