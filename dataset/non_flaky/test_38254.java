class DummyClass_38254 {
    @Test
    public void testGetRangeWithHistoryThrowsOnError() {
        try {
            keyValueService.getRangeWithHistory(TEST_NONEXISTING_TABLE, RangeRequest.all(), Long.MAX_VALUE).hasNext();
            Assert.fail("getRangeWithHistory must throw on failure");
        } catch (RuntimeException e) {
            // Expected
        }
    }

}