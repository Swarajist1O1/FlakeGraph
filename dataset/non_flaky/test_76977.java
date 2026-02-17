class DummyClass_76977 {
  @Test
  public void readTest5() {
    String testAppId = "localReadTest5";
    ShuffleReadClientImpl readClient = new ShuffleReadClientImpl(StorageType.LOCALFILE.name(),
        testAppId, 0, 1, 100, 2, 10, 1000,
        "", Roaring64NavigableMap.bitmapOf(), Roaring64NavigableMap.bitmapOf(),
        shuffleServerInfo, null);
    assertNull(readClient.readShuffleBlockData());
    readClient.checkProcessedBlockIds();
  }

}