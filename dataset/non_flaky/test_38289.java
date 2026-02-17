class DummyClass_38289 {
    @Test
    public void testRangePageBatchSizeOne() {
        RangeRequest rangeRequest = RangeRequest.builder().batchHint(1).build();
        Map<RangeRequest, TokenBackedBasicResultsPage<RowResult<Value>, byte[]>> ranges = keyValueService.getFirstBatchForRanges(TEST_TABLE, Collections.singleton(rangeRequest), 1);
        assertEquals(1, ranges.keySet().size());
        assertEquals(0, ranges.values().iterator().next().getResults().size());
        assertEquals(false, ranges.values().iterator().next().moreResultsAvailable());
    }

}