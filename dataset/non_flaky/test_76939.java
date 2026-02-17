class DummyClass_76939 {
  @Test
  public void getBlockIdTest() {
    // max value of blockId
    assertEquals(
        new Long(9223372036854775807L), ClientUtils.getBlockId(16777215, 1048575, 524287));
    // just a random test
    assertEquals(
        new Long(1759218709299300L), ClientUtils.getBlockId(100, 100, 100));
    // min value of blockId
    assertEquals(
        new Long(0L), ClientUtils.getBlockId(0, 0, 0));
    try {
      ClientUtils.getBlockId(16777216, 0, 0);
      fail(EXCEPTION_EXPECTED);
    } catch (Exception e) {
      assertTrue(e.getMessage().contains("Can't support partitionId[16777216], the max value should be 16777215"));
    }
    try {
      ClientUtils.getBlockId(0, 1048576, 0);
      fail(EXCEPTION_EXPECTED);
    } catch (Exception e) {
      assertTrue(e.getMessage().contains("Can't support taskAttemptId[1048576], the max value should be 1048575"));
    }
    try {
      ClientUtils.getBlockId(0, 0, 524288);
      fail(EXCEPTION_EXPECTED);
    } catch (Exception e) {
      assertTrue(e.getMessage().contains("Can't support sequence[524288], the max value should be 524287"));
    }
  }

}