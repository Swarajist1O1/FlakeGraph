class DummyClass_38279 {
    @Test
    public void testKeyValueRangeColumnSelection() {
        putDirect("row1", "col1", "v1", 0);
        putDirect("row1", "col2", "v2", 2);
        putDirect("row1", "col4", "v5", 3);
        putDirect("row1a", "col4", "v5", 100);
        putDirect("row2", "col2", "v3", 1);
        putDirect("row2", "col4", "v4", 6);

        List<byte[]> selectedColumns = ImmutableList.of(PtBytes.toBytes("col2"));
        RangeRequest simpleRange = RangeRequest.builder().retainColumns(ColumnSelection.create(selectedColumns)).build();
        ImmutableList<RowResult<Value>> list = ImmutableList.copyOf(keyValueService.getRange(TEST_TABLE, simpleRange, 1));
        assertEquals(0, list.size());

        list = ImmutableList.copyOf(keyValueService.getRange(TEST_TABLE, simpleRange, 2));
        assertEquals(1, list.size());
        RowResult<Value> row = list.iterator().next();
        assertEquals(1, row.getColumns().size());

        list = ImmutableList.copyOf(keyValueService.getRange(TEST_TABLE, simpleRange, 3));
        assertEquals(2, list.size());
        row = list.iterator().next();
        assertEquals(1, row.getColumns().size());

        list = ImmutableList.copyOf(keyValueService.getRange(TEST_TABLE, simpleRange.getBuilder().endRowExclusive(PtBytes.toBytes("row2")).build(), 3));
        assertEquals(1, list.size());
        row = list.iterator().next();
        assertEquals(1, row.getColumns().size());

        list = ImmutableList.copyOf(keyValueService.getRange(TEST_TABLE, simpleRange.getBuilder().startRowInclusive(PtBytes.toBytes("row1a")).build(), 3));
        assertEquals(1, list.size());
        row = list.iterator().next();
        assertEquals(1, row.getColumns().size());
    }

}