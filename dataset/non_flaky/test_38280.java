class DummyClass_38280 {
    @Test
    public void testKeyValueRangeWithDeletes() {
        putDirect("row1", "col1", "", 0);

        ImmutableList<RowResult<Value>> list = ImmutableList.copyOf(keyValueService.getRange(TEST_TABLE, RangeRequest.builder().build(), 1));
        assertEquals(1, list.size());
        RowResult<Value> row = list.iterator().next();
        assertEquals(1, row.getColumns().size());
    }

}