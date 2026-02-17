class DummyClass_38208 {
    @Test
    public void testGetRange2() {
        final Cell cell = Cell.create(",r,1".getBytes(), ",c,1,".getBytes());
        db.put(TABLE, ImmutableMap.of(cell, "v1".getBytes()), 2);
        final RangeRequest range = RangeRequest.builder().build();
        final ClosableIterator<RowResult<Value>> it = db.getRange(TABLE, range, 10);
        try {
            final List<RowResult<Value>> list = Lists.newArrayList();
            Iterators.addAll(list, it);
            assertEquals(1, list.size());
            final RowResult<Value> row = list.iterator().next();
            final Map<Cell, Value> cellsFromRow = putAll(Maps.<Cell, Value>newHashMap(), row.getCells());
            final Map<Cell, Value> rows = db.getRows(TABLE, ImmutableList.of(",r,1".getBytes()), ColumnSelection.all(), 3);
            assertEquals(rows, cellsFromRow);
        } finally {
            it.close();
        }
    }

}