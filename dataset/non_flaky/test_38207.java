class DummyClass_38207 {
    @Test
    public void testGetRange() {
        final Cell cell = Cell.create("r1".getBytes(), "c1".getBytes());
        final Cell cell2 = Cell.create("r1".getBytes(), "c2".getBytes());
        final Cell cell3 = Cell.create("r2".getBytes(), "c2".getBytes());
        db.put(TABLE, ImmutableMap.of(cell, "v1".getBytes()), 2);
        db.put(TABLE, ImmutableMap.of(cell2, "v2".getBytes()), 2);
        db.put(TABLE, ImmutableMap.of(cell3, "v3".getBytes()), 4);
        final RangeRequest range = RangeRequest.builder().endRowExclusive("r2".getBytes()).build();
        final ClosableIterator<? extends RowResult<Value>> it = db.getRange(TABLE, range, 10);
        try {
            final List<RowResult<Value>> list = Lists.newArrayList();
            Iterators.addAll(list, it);
            assertEquals(1, list.size());
            final Map<Cell, Value> rows = db.getRows(TABLE, ImmutableList.of("r1".getBytes()), ColumnSelection.all(), 3);
            assertEquals(2, rows.size());
            final RowResult<Value> row = list.iterator().next();
            final Map<Cell, Value> cellsFromRow = putAll(Maps.<Cell, Value>newHashMap(), row.getCells());
            assertEquals(rows, cellsFromRow);
        } finally {
            it.close();
        }
    }

}