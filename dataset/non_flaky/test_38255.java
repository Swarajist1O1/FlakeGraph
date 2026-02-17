class DummyClass_38255 {
    @Test
    public void testGetRangeOfTimestampsThrowsOnError() {
        try {
            keyValueService.getRangeOfTimestamps(TEST_NONEXISTING_TABLE, RangeRequest.all(), Long.MAX_VALUE).hasNext();
            Assert.fail("getRangeOfTimestamps must throw on failure");
        } catch (RuntimeException e) {
            // Expected
        }
    }

}