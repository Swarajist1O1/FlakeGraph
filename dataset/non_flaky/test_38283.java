class DummyClass_38283 {
    @Test
    public void testKeyValueRangesMany3() {
        putDirect("row1", "col1", "", 0);
        putDirect("row2", "col1", "", 0);
        putDirect("row2", "col2", "", 0);

        RangeRequest allRange = RangeRequest.builder().prefixRange("row1".getBytes()).batchHint(3).build();
        for (int i = 0 ; i < 1000 ; i++) {
            ClosableIterator<RowResult<Value>> range = keyValueService.getRange(TEST_TABLE, allRange, 1);
            ImmutableList<RowResult<Value>> list = ImmutableList.copyOf(range);
            assertEquals(1, list.size());
        }
    }

}