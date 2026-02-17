class DummyClass_38277 {
    @Test
    public void testKeyValueRange() {
        putDirect("row1", "col1", "v1", 0);
        putDirect("row1", "col2", "v2", 2);
        putDirect("row1", "col4", "v5", 3);
        putDirect("row1a", "col4", "v5", 100);
        putDirect("row2", "col2", "v3", 1);
        putDirect("row2", "col4", "v4", 6);

        ImmutableList<RowResult<Value>> list = ImmutableList.copyOf(keyValueService.getRange(TEST_TABLE, RangeRequest.builder().build(), 1));
        assertEquals(1, list.size());
        RowResult<Value> row = list.iterator().next();
        assertEquals(1, row.getColumns().size());

        list = ImmutableList.copyOf(keyValueService.getRange(TEST_TABLE, RangeRequest.builder().build(), 2));
        assertEquals(2, list.size());
        row = list.iterator().next();
        assertEquals(1, row.getColumns().size());

        list = ImmutableList.copyOf(keyValueService.getRange(TEST_TABLE, RangeRequest.builder().build(), 3));
        assertEquals(2, list.size());
        row = list.iterator().next();
        assertEquals(2, row.getColumns().size());

        list = ImmutableList.copyOf(keyValueService.getRange(TEST_TABLE, RangeRequest.builder().endRowExclusive(PtBytes.toBytes("row2")).build(), 3));
        assertEquals(1, list.size());
        row = list.iterator().next();
        assertEquals(2, row.getColumns().size());

        list = ImmutableList.copyOf(keyValueService.getRange(TEST_TABLE, RangeRequest.builder().startRowInclusive(PtBytes.toBytes("row1a")).build(), 3));
        assertEquals(1, list.size());
        row = list.iterator().next();
        assertEquals(1, row.getColumns().size());
    }

}