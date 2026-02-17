class DummyClass_76929 {
  @Test
  public void addRecordTest() {
    SparkConf conf = getConf();
    WriteBufferManager wbm = createManager(conf);
    wbm.setShuffleWriteMetrics(new ShuffleWriteMetrics());
    String testKey = "Key";
    String testValue = "Value";
    List<ShuffleBlockInfo> result = wbm.addRecord(0, testKey, testValue);
    // single buffer is not full, there is no data return
    assertEquals(0, result.size());
    assertEquals(512, wbm.getAllocatedBytes());
    assertEquals(32, wbm.getUsedBytes());
    assertEquals(0, wbm.getInSendListBytes());
    assertEquals(1, wbm.getBuffers().size());
    wbm.addRecord(0, testKey, testValue);
    wbm.addRecord(0, testKey, testValue);
    wbm.addRecord(0, testKey, testValue);
    result = wbm.addRecord(0, testKey, testValue);
    // single buffer is full
    assertEquals(1, result.size());
    assertEquals(512, wbm.getAllocatedBytes());
    assertEquals(96, wbm.getUsedBytes());
    assertEquals(96, wbm.getInSendListBytes());
    assertEquals(0, wbm.getBuffers().size());
    wbm.addRecord(0, testKey, testValue);
    wbm.addRecord(1, testKey, testValue);
    wbm.addRecord(2, testKey, testValue);
    // single buffer is not full, and less than spill size
    assertEquals(512, wbm.getAllocatedBytes());
    assertEquals(192, wbm.getUsedBytes());
    assertEquals(96, wbm.getInSendListBytes());
    assertEquals(3, wbm.getBuffers().size());
    // all buffer size > spill size
    wbm.addRecord(3, testKey, testValue);
    wbm.addRecord(4, testKey, testValue);
    result = wbm.addRecord(5, testKey, testValue);
    assertEquals(6, result.size());
    assertEquals(512, wbm.getAllocatedBytes());
    assertEquals(288, wbm.getUsedBytes());
    assertEquals(288, wbm.getInSendListBytes());
    assertEquals(0, wbm.getBuffers().size());
    // free memory
    wbm.freeAllocatedMemory(96);
    assertEquals(416, wbm.getAllocatedBytes());
    assertEquals(192, wbm.getUsedBytes());
    assertEquals(192, wbm.getInSendListBytes());

    assertEquals(11, wbm.getShuffleWriteMetrics().recordsWritten());
    assertTrue(wbm.getShuffleWriteMetrics().bytesWritten() > 0);

    wbm.freeAllocatedMemory(192);
    wbm.addRecord(0, testKey, testValue);
    wbm.addRecord(1, testKey, testValue);
    wbm.addRecord(2, testKey, testValue);
    result = wbm.clear();
    assertEquals(3, result.size());
    assertEquals(224, wbm.getAllocatedBytes());
    assertEquals(96, wbm.getUsedBytes());
    assertEquals(96, wbm.getInSendListBytes());
  }

}