class DummyClass_38281 {
    @Test
    public void testKeyValueRanges() {
        putDirect("row1", "col1", "", 0);
        putDirect("row2", "col1", "", 0);
        putDirect("row2", "col2", "", 0);

        Map<RangeRequest, TokenBackedBasicResultsPage<RowResult<Value>, byte[]>> ranges = keyValueService.getFirstBatchForRanges(TEST_TABLE, ImmutableList.of(RangeRequest.builder().build(), RangeRequest.builder().build()), 1);
        assertTrue(ranges.size() >= 1);
    }

}