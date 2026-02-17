class DummyClass_38253 {
    @Test
    public void testGetRangeThrowsOnError() {
        try {
            keyValueService.getRange(TEST_NONEXISTING_TABLE, RangeRequest.all(), Long.MAX_VALUE).hasNext();
            Assert.fail("getRange must throw on failure");
        } catch (RuntimeException e) {
            // Expected
        }
    }

}