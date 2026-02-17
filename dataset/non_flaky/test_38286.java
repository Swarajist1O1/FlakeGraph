class DummyClass_38286 {
    @Test
    public void testRangePagingBatchesReverse() {
        if (!supportsReverse()) {
            return;
        }
        int totalPuts = 101;
        for (int i = 0 ; i < totalPuts ; i++) {
            putDirect("row"+i, "col1", "v1", 0);
        }

        Map<RangeRequest, TokenBackedBasicResultsPage<RowResult<Value>, byte[]>> ranges = keyValueService.getFirstBatchForRanges(TEST_TABLE, Iterables.limit(Iterables.cycle(RangeRequest.reverseBuilder().batchHint(1000).build()), 100), 1);
        assertEquals(1, ranges.keySet().size());
        assertEquals(totalPuts, ranges.values().iterator().next().getResults().size());
    }

}