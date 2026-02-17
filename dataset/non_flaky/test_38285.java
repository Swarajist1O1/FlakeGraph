class DummyClass_38285 {
    @Test
    public void testRangePagingBatches() {
        int totalPuts = 101;
        for (int i = 0 ; i < totalPuts ; i++) {
            putDirect("row"+i, "col1", "v1", 0);
        }

        Map<RangeRequest, TokenBackedBasicResultsPage<RowResult<Value>, byte[]>> ranges = keyValueService.getFirstBatchForRanges(TEST_TABLE, Iterables.limit(Iterables.cycle(RangeRequest.builder().batchHint(1000).build()), 100), 1);
        assertEquals(1, ranges.keySet().size());
        assertEquals(totalPuts, ranges.values().iterator().next().getResults().size());
    }

}