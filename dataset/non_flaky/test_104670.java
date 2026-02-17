class DummyClass_104670 {
  @Test
  public void testTextSearchCountQuery()
      throws Exception {
    // Keep posting queries until all records are consumed
    long previousResult = 0;
    while (getCurrentCountStarResult() < NUM_RECORDS) {
      long result = getTextColumnQueryResult();
      assertTrue(result >= previousResult);
      previousResult = result;
      Thread.sleep(100);
    }

    //Lucene index on consuming segments to update the latest records
    TestUtils.waitForCondition(aVoid -> {
      try {
        return getTextColumnQueryResult() == NUM_MATCHING_RECORDS;
      } catch (Exception e) {
        fail("Caught exception while getting text column query result");
        return false;
      }
    }, 10_000L, "Failed to reach expected number of matching records");
  }

}