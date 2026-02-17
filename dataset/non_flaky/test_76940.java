class DummyClass_76940 {
  @Test
  public void getBitmapNumTest() {
    // max value of taskNum, partitionNum, blockNumPerTaskPerPartition, it is unexpected in real job
    assertEquals(
        2147483647, ClientUtils.getBitmapNum(Integer.MAX_VALUE, Integer.MAX_VALUE, 1000000, 100000000L));
    // taskNum * partitionNum * blockNumPerTaskPerPartition / blockNumPerBitmap > 0
    assertEquals(
        5001, ClientUtils.getBitmapNum(100000, 100000, 50, 100000000L));
    // taskNum * partitionNum * blockNumPerTaskPerPartition / blockNumPerBitmap = 0
    assertEquals(
        1, ClientUtils.getBitmapNum(1999, 1999, 50, 100000000L));
    try {
      ClientUtils.getBitmapNum(1, 1, 1, 19999999L);
      fail(EXCEPTION_EXPECTED);
    } catch (Exception e) {
      assertTrue(e.getMessage().contains("blockNumPerBitmap should be greater than"));
    }
    try {
      ClientUtils.getBitmapNum(1, 1, 1000001, 20000000L);
      fail(EXCEPTION_EXPECTED);
    } catch (Exception e) {
      assertTrue(e.getMessage().contains("blockNumPerTaskPerPartition should be less than"));
    }
  }

}