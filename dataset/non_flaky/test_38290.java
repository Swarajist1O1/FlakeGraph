class DummyClass_38290 {
    @Test
    public void testRangeAfterTimestmap() {
        putDirect("row1", "col2", "", 5);
        putDirect("row2", "col2", "", 0);
        RangeRequest rangeRequest = RangeRequest.builder().batchHint(1).build();
        Map<RangeRequest, TokenBackedBasicResultsPage<RowResult<Value>, byte[]>> ranges = keyValueService.getFirstBatchForRanges(TEST_TABLE, Collections.singleton(rangeRequest), 1);
        assertEquals(1, ranges.keySet().size());
        TokenBackedBasicResultsPage<RowResult<Value>, byte[]> page = ranges.values().iterator().next();
        assertTrue(!page.getResults().isEmpty() || page.moreResultsAvailable());
    }

}