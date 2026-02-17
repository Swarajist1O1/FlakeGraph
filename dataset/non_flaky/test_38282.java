class DummyClass_38282 {
    @Test
    public void testKeyValueRanges2() {
        putDirect("row1", "col1", "", 0);
        putDirect("row2", "col1", "", 0);
        putDirect("row2", "col2", "", 0);

        final RangeRequest allRange = RangeRequest.builder().build();
        final RangeRequest oneRange = RangeRequest.builder().startRowInclusive("row2".getBytes()).build();
        final RangeRequest allRangeBatch = RangeRequest.builder().batchHint(3).build();
        Map<RangeRequest, TokenBackedBasicResultsPage<RowResult<Value>, byte[]>> ranges = keyValueService.getFirstBatchForRanges(TEST_TABLE, ImmutableList.of(allRange, oneRange, allRangeBatch), 1);
        assertTrue(ranges.get(allRange).getResults().size()>=1);
        assertEquals(2, ranges.get(allRangeBatch).getResults().size());
        assertFalse(ranges.get(allRangeBatch).moreResultsAvailable());
        assertEquals(1, ranges.get(oneRange).getResults().size());
    }

}