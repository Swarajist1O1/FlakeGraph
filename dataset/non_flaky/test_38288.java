class DummyClass_38288 {
    @Test
    public void testRangePagingBatchSizeOneReverse() {
        if (!supportsReverse()) {
            return;
        }
        int totalPuts = 100;
        for (int i = 0 ; i < totalPuts ; i++) {
            putDirect("row"+i, "col1", "v1", 0);
        }

        RangeRequest rangeRequest = RangeRequest.reverseBuilder().batchHint(1).build();
        Map<RangeRequest, TokenBackedBasicResultsPage<RowResult<Value>, byte[]>> ranges = keyValueService.getFirstBatchForRanges(TEST_TABLE, Iterables.limit(Iterables.cycle(rangeRequest), 100), 1);
        assertEquals(1, ranges.keySet().size());
        assertEquals(1, ranges.values().iterator().next().getResults().size());
        assertEquals("row99", PtBytes.toString(ranges.values().iterator().next().getResults().iterator().next().getRowName()));
    }

}