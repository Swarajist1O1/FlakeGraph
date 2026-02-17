class DummyClass_76948 {
  @Test
  public void readTest9() {
    // empty data
    ShuffleReadClientImpl readClient = new ShuffleReadClientImpl(StorageType.HDFS.name(),
        "appId", 0, 1, 100, 2, 10, 1000,
        "basePath", Roaring64NavigableMap.bitmapOf(), Roaring64NavigableMap.bitmapOf(),
        Lists.newArrayList(), new Configuration());
    assertNull(readClient.readShuffleBlockData());
    readClient.checkProcessedBlockIds();
  }

}