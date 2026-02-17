class DummyClass_38209 {
    @Test
    public void testGetRowCellOverlap() {
        final Cell cell = Cell.create("12".getBytes(), "34".getBytes());
        final Cell cell2 = Cell.create("1".getBytes(), "23".getBytes());
        db.put(TABLE, ImmutableMap.of(cell, "v1".getBytes()), 2);
        db.put(TABLE, ImmutableMap.of(cell2, "v2".getBytes()), 2);
        final Map<Cell, Value> rows = db.getRows(TABLE, ImmutableList.of("12".getBytes()), ColumnSelection.all(), 3);
        assertEquals(1, rows.size());
    }

}