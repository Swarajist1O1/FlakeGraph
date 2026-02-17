class DummyClass_76930 {
  @Test
  public void createBlockIdTest() {
    SparkConf conf = getConf();
    WriteBufferManager wbm = createManager(conf);
    WriterBuffer mockWriterBuffer = mock(WriterBuffer.class);
    when(mockWriterBuffer.getData()).thenReturn(new byte[]{});
    when(mockWriterBuffer.getMemoryUsed()).thenReturn(0);
    ShuffleBlockInfo sbi = wbm.createShuffleBlock(0, mockWriterBuffer);
    // seqNo = 0, partitionId = 0, taskId = 0
    assertEquals(0L, sbi.getBlockId());

    // seqNo = 1, partitionId = 0, taskId = 0
    sbi = wbm.createShuffleBlock(0, mockWriterBuffer);
    assertEquals(17592186044416L, sbi.getBlockId());

    // seqNo = 0, partitionId = 1, taskId = 0
    sbi = wbm.createShuffleBlock(1, mockWriterBuffer);
    assertEquals(1048576L, sbi.getBlockId());

    // seqNo = 1, partitionId = 1, taskId = 0
    sbi = wbm.createShuffleBlock(1, mockWriterBuffer);
    assertEquals(17592187092992L, sbi.getBlockId());
  }

}